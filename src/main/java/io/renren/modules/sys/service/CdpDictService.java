package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.CdpDictEntity;

import java.util.Map;

/**
 * 字典表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-27 15:39:28
 */
public interface CdpDictService extends IService<CdpDictEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

