package io.renren.common.base;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import com.google.common.collect.Lists;
import io.renren.common.utils.Audit;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean insert(T t){
        return super.insert(initInsert(t));
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean insertBatch(List<T> entityList) {
        return this.insertBatch(entityList, 30);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean insertBatch(List<T> entityList, int batchSize) {
        List<T> ts = entityList.stream().filter(t -> {
                return null != t;
        }).map(t -> initInsert(t)).collect(Collectors.toList());
        return super.insertBatch(ts, batchSize);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateById(T t){
        return super.updateById(initUpdate(t));
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateBatchById(List<T> entityList) {
        return this.updateBatchById(entityList, 30);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateBatchById(List<T> entityList, int batchSize) {
        List<T> ts = entityList.stream().filter(t -> {
            if(t == null){
                return false;
            }
            TableInfo tableInfo = TableInfoHelper.getTableInfo(t.getClass());
            Object idVal = ReflectionKit.getMethodValue(t.getClass(), t, tableInfo.getKeyProperty());
            return !StringUtils.checkValNull(idVal);
        }).map(t -> initUpdate(t)).collect(Collectors.toList());

        return super.updateBatchById(ts, batchSize);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean update(T entity, Wrapper<T> wrapper) {
        return super.update(initUpdate(entity),wrapper);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateAllColumnById(T t){
        return super.updateAllColumnById(initUpdate(t));
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean deleteById(Serializable id){
        T t = super.selectById(id);
        if(t == null){
            return false;
        }
        if(!hasAttribute(t.getClass(),"deleted")){
            return super.deleteById(id);
        }
        return super.updateById(initDeleted(t));
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean deleteByMap(Map<String, Object> columnMap){
        List<T> list = super.selectByMap(columnMap);
        if(CollectionUtils.isEmpty(list)){
            return false;
        }
        Class clazz = list.get(0).getClass();
        if(!hasAttribute(list.get(0).getClass(),"deleted")){
            TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
            List<Serializable> idList = Lists.newArrayList();
            if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                idList = Stream.of(list).map(t -> {
                    return (Serializable) ReflectionKit.getMethodValue(clazz, t, tableInfo.getKeyProperty());
                }).filter(id -> !StringUtils.checkValNull(id)).collect(Collectors.toList());
            }
            return super.deleteBatchIds(idList);
        }
        List<T> ts = list.stream().map(t -> initDeleted(t)).collect(Collectors.toList());

        return super.updateBatchById(ts);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean insertOrUpdate(T entity){
        if (null == entity) {
            return false;
        } else {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idVal)) {
                    return this.insert(initInsert(entity));
                }else{
                    return this.updateById(initUpdate(entity)) || this.insert(initInsert(entity));
                }
            } else {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }
        }
    }

    public T initInsert(T t) {
        if (t == null) {
            return t;
        }
        String[] fields = new String[]{"createdBy", "createdName", "creationDate", "lastUpdatedBy",
                "lastUpdatedName", "lastUpdateDate"};
        return invoke(t, fields);
    }

    public T initUpdate(T t) {
        if(t == null){
            return t;
        }
        String[] fields= new String[]{"lastUpdatedBy", "lastUpdatedName", "lastUpdateDate"};
        return invoke(t, fields);
    }

    public T initDeleted(T t) {
        if(t == null){
            return t;
        }

        Method method = ReflectionUtils.findMethod(t.getClass(), "setDeleted", String.class);
        ReflectionUtils.invokeMethod(method, t, "1");
        String[] fields= new String[]{"deletedBy","deletedName","deletedDate","lastUpdatedBy", "lastUpdatedName", "lastUpdateDate"};
        return invoke(t, fields);
    }

    private boolean hasAttribute(Class clazz, String attribute){
        Field field = ReflectionUtils.findField(clazz,attribute);
        return field != null;
    }

    private T invoke(T t, String[] attributes){
        if(null == attributes || attributes.length == 0){
            return t;
        }
        Audit audit = new Audit(-1,"系统");
        SysUserEntity userEntity = (SysUserEntity)SecurityUtils.getSubject().getPrincipal();
        if(userEntity != null){
            audit = new Audit(userEntity.getUserId(),userEntity.getUsername());
        }
        for (String attribute : attributes){
            Field field = ReflectionUtils.findField(t.getClass(),attribute);
            if(field == null){
                continue;
            }
            Method method = ReflectionUtils.findMethod(t.getClass(),
                    "set"+field.getName().toUpperCase().substring(0, 1)+field.getName().substring(1),
                    field.getType());
            if(method == null){
                continue;
            }

            if(field.getType().getSimpleName().equals("String")){
                ReflectionUtils.invokeMethod(method, t, audit.getName());
            }else if(field.getType().getSimpleName().equals("Long")){
                ReflectionUtils.invokeMethod(method, t, audit.getId());
            }else if(field.getType().getSimpleName().equals("Date")){
                ReflectionUtils.invokeMethod(method, t, new Date());
            }
        }
        return t;
    }
}
