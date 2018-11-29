package io.renren.modules.map.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.map.entity.MapDirEntity;
import io.renren.modules.map.service.MapDirService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-21 17:04:59
 */
@RestController
@RequestMapping("map/mapdir")
public class MapDirController {
    @Autowired
    private MapDirService mapDirService;

    /**
     * 列表
     */
    @RequestMapping("/page")
    @RequiresPermissions("map:mapdir:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mapDirService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/list")
    @RequiresPermissions("map:mapdir:list")
    public List<MapDirEntity> getList(@RequestParam Map<String, Object> params){
        EntityWrapper<MapDirEntity> w = new EntityWrapper<MapDirEntity>();
        w.orderBy("seq");
        List<MapDirEntity> menuList = mapDirService.selectList(w);
        for(MapDirEntity mapDirEntity : menuList){
            MapDirEntity parentDirEntity = mapDirService.selectById(mapDirEntity.getParentId());
            if(parentDirEntity != null){
                mapDirEntity.setParentName(parentDirEntity.getName());
            }
        }
        return menuList;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("map:mapdir:info")
    public R info(@PathVariable("id") Long id){
        MapDirEntity mapDir = mapDirService.selectById(id);

        return R.ok().put("dir", mapDir);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("map:mapdir:save")
    public R save(@RequestBody MapDirEntity mapDir){
        mapDirService.insert(mapDir);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("map:mapdir:update")
    public R update(@RequestBody MapDirEntity mapDir){
        ValidatorUtils.validateEntity(mapDir);
        //全部更新
        mapDirService.updateAllColumnById(mapDir);
        
        return R.ok();
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("map:dir:select")
    public R select(){
        //查询列表数据
        List<MapDirEntity> menuList = mapDirService.queryNoParent();

        //添加顶级菜单
        MapDirEntity root = new MapDirEntity();
        root.setId(0L);
        root.setName("顶级目录");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("dirList", menuList);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("map:mapdir:delete")
    public R delete(@RequestBody Long[] ids){
        mapDirService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
