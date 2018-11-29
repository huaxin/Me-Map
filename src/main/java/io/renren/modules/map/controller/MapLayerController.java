package io.renren.modules.map.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.map.entity.MapLayerEntity;
import io.renren.modules.map.service.MapLayerService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-21 17:04:59
 */
@RestController
@RequestMapping("map/maplayer")
public class MapLayerController {
    @Autowired
    private MapLayerService mapLayerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("map:maplayer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mapLayerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("map:maplayer:info")
    public R info(@PathVariable("id") Long id){
        MapLayerEntity mapLayer = mapLayerService.selectById(id);

        return R.ok().put("mapLayer", mapLayer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("map:maplayer:save")
    public R save(@RequestBody MapLayerEntity mapLayer){
        mapLayerService.insert(mapLayer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("map:maplayer:update")
    public R update(@RequestBody MapLayerEntity mapLayer){
        ValidatorUtils.validateEntity(mapLayer);
        mapLayerService.updateAllColumnById(mapLayer);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("map:maplayer:delete")
    public R delete(@RequestBody Long[] ids){
        mapLayerService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
