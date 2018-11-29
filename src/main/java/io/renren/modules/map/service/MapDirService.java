package io.renren.modules.map.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.map.entity.MapDirEntity;
import io.renren.modules.sys.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-21 17:04:59
 */
public interface MapDirService extends IService<MapDirEntity> {

    List<MapDirEntity> queryListByParentId(Long parentId);

    PageUtils queryPage(Map<String, Object> params);

    List<MapDirEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    List<MapDirEntity> queryNoParent();

    List<MapDirEntity> getUserDirList(Long userId);
}

