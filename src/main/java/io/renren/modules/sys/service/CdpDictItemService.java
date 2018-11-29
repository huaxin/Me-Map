package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.CdpDictItemEntity;

import java.util.Map;

/**
 * 字典条目表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-27 15:39:28
 */
public interface CdpDictItemService extends IService<CdpDictItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

