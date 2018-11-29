package io.renren.modules.map.dao;

import io.renren.modules.map.entity.MapDirEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-21 17:04:59
 */
public interface MapDirDao extends BaseMapper<MapDirEntity> {

    /**
     * 获取没有父节点的目录列表
     */
    List<MapDirEntity> queryNoParent();
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<MapDirEntity> queryListByParentId(Long parentId);

}
