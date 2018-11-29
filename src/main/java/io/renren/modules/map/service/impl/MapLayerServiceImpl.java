package io.renren.modules.map.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.base.BaseServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.map.dao.MapLayerDao;
import io.renren.modules.map.entity.MapLayerEntity;
import io.renren.modules.map.service.MapLayerService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("mapLayerService")
public class MapLayerServiceImpl extends BaseServiceImpl<MapLayerDao, MapLayerEntity> implements MapLayerService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<MapLayerEntity> wrapper = new EntityWrapper<>();
        Long dirId = MapUtils.getLong(params,"dirId");
        if(dirId !=null && 0 != dirId){
            wrapper.eq("dir_id", params.get("dirId"));
        }
        logger.info("queryPage condition:{}", wrapper.getSqlSegment());
        Page<MapLayerEntity> page = this.selectPage(
                new Query<MapLayerEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
