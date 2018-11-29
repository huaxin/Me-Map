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

import io.renren.modules.sys.entity.CdpDictEntity;
import io.renren.modules.sys.service.CdpDictService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 字典表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-27 15:39:28
 */
@RestController
@RequestMapping("sys/cdpdict")
public class CdpDictController {
    @Autowired
    private CdpDictService cdpDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:cdpdict:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cdpDictService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:cdpdict:info")
    public R info(@PathVariable("id") Long id){
        CdpDictEntity cdpDict = cdpDictService.selectById(id);

        return R.ok().put("cdpDict", cdpDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:cdpdict:save")
    public R save(@RequestBody CdpDictEntity cdpDict){
        cdpDictService.insert(cdpDict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:cdpdict:update")
    public R update(@RequestBody CdpDictEntity cdpDict){
        ValidatorUtils.validateEntity(cdpDict);
        cdpDictService.updateAllColumnById(cdpDict);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:cdpdict:delete")
    public R delete(@RequestBody Long[] ids){
        cdpDictService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
