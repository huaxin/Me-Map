package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.base.BaseServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.CdpDictItemDao;
import io.renren.modules.sys.entity.CdpDictItemEntity;
import io.renren.modules.sys.service.CdpDictItemService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service("cdpDictItemService")
public class CdpDictItemServiceImpl extends BaseServiceImpl<CdpDictItemDao, CdpDictItemEntity> implements CdpDictItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CdpDictItemEntity> page = this.selectPage(
                new Query<CdpDictItemEntity>(params).getPage(),
                new EntityWrapper<CdpDictItemEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean insert(CdpDictItemEntity entity){
        if(entity != null){
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            entity.setCreatedBy(-1L);
            entity.setCreatedName("系统");
            entity.setCreationDate(new Date());
            entity.setLastUpdatedBy(-1L);
            entity.setLastUpdatedName("系统");
            entity.setLastUpdateDate(new Date());
        }
        return super.insert(entity);

    }

}
