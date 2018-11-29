package io.renren.modules.sys.controller;

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

import io.renren.modules.sys.entity.CdpDictItemEntity;
import io.renren.modules.sys.service.CdpDictItemService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 字典条目表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-27 15:39:28
 */
@RestController
@RequestMapping("sys/cdpdictitem")
public class CdpDictItemController {
    @Autowired
    private CdpDictItemService cdpDictItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:cdpdictitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cdpDictItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:cdpdictitem:info")
    public R info(@PathVariable("id") Long id){
        CdpDictItemEntity cdpDictItem = cdpDictItemService.selectById(id);

        return R.ok().put("cdpDictItem", cdpDictItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:cdpdictitem:save")
    public R save(@RequestBody CdpDictItemEntity cdpDictItem){
        cdpDictItemService.insert(cdpDictItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:cdpdictitem:update")
    public R update(@RequestBody CdpDictItemEntity cdpDictItem){
        ValidatorUtils.validateEntity(cdpDictItem);
        cdpDictItemService.updateAllColumnById(cdpDictItem);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:cdpdictitem:delete")
    public R delete(@RequestBody Long[] ids){
        cdpDictItemService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
