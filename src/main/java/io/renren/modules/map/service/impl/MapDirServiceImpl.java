package io.renren.modules.map.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.base.BaseServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.map.dao.MapDirDao;
import io.renren.modules.map.entity.MapDirEntity;
import io.renren.modules.map.service.MapDirService;
import io.renren.modules.sys.entity.SysMenuEntity;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("mapDirService")
public class MapDirServiceImpl extends BaseServiceImpl<MapDirDao, MapDirEntity> implements MapDirService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<MapDirEntity> queryListParentId(Long parentId, List<Long> dirIdList) {
        List<MapDirEntity> menuList = queryListByParentId(parentId);
        if(dirIdList == null){
            return menuList;
        }

        List<MapDirEntity> userMenuList = new ArrayList<>();
        for(MapDirEntity menu : menuList){
            if(dirIdList.contains(menu.getId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<MapDirEntity> queryNoParent(){
        return baseMapper.queryNoParent();
    }

    @Override
    public List<MapDirEntity> queryListByParentId(Long parentId) {
        return baseMapper.queryListByParentId(parentId);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MapDirEntity> page = this.selectPage(
                new Query<MapDirEntity>(params).getPage(),
                new EntityWrapper<MapDirEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<MapDirEntity> getUserDirList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> dirIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(dirIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<MapDirEntity> getAllMenuList(List<Long> dirIdList){
        //查询根菜单列表
        List<MapDirEntity> dirList = queryListParentId(0L, dirIdList);
        //递归获取子菜单
        getDirTreeList(dirList, dirIdList);

        return dirList;
    }

    /**
     * 递归
     */
    private List<MapDirEntity> getDirTreeList(List<MapDirEntity> dirList, List<Long> dirIdList){
        List<MapDirEntity> subMenuList = new ArrayList<>();

        for(MapDirEntity entity : dirList){
            //目录
            if(Constant.DirType.CATALOG.getValue().equals(entity.getType())){
                entity.setList(getDirTreeList(queryListParentId(entity.getId(), dirIdList), dirIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

}
