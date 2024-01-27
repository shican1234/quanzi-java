package io.renren.common.modules.fabulous.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.fabulous.dto.FabulousDTO;
import io.renren.common.modules.fabulous.entity.FabulousEntity;

/**
 * 点赞记录表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
public interface FabulousService extends CrudService<FabulousEntity, FabulousDTO> {

    FabulousEntity queryTrenIdAndUserId(Long tid, Long id,Integer type);

    void deleteByPostId(Long id,int type);
}