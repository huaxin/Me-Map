package io.renren.modules.map.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.map.entity.MapLayerEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-21 17:04:59
 */
public interface MapLayerService extends IService<MapLayerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

