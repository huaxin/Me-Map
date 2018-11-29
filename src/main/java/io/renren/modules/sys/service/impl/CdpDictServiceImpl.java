package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.base.BaseServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.CdpDictDao;
import io.renren.modules.sys.entity.CdpDictEntity;
import io.renren.modules.sys.service.CdpDictService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service("cdpDictService")
public class CdpDictServiceImpl extends BaseServiceImpl<CdpDictDao, CdpDictEntity> implements CdpDictService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CdpDictEntity> page = this.selectPage(
                new Query<CdpDictEntity>(params).getPage(),
                new EntityWrapper<CdpDictEntity>()
        );

        return new PageUtils(page);
    }

}
