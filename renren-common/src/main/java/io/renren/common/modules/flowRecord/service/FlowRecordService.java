package io.renren.common.modules.flowRecord.service;

import io.renren.common.dto.PayRecordUserListDTO;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.service.CrudService;
import io.renren.common.modules.flowRecord.dto.FlowRecordDTO;
import io.renren.common.modules.flowRecord.entity.FlowRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 金币流水
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-14
 */
public interface FlowRecordService extends CrudService<FlowRecordEntity, FlowRecordDTO> {

    /**
     * 根据用户ID查询用户的金币流水数据
     *
     */
    List<FlowRecordEntity> getFlowRecordByUserId(Map<String,Object> map);

    FlowRecordEntity selectEntityByResIdAndStatusAndUserId(Long awayUserId,Long outUserId, int status);

    List<FlowRecordEntity> selectEntityByOutUserIdAndStatusAndTid(Long id, int i,Long tid);

    List<UserEntity> payRecordUserList(PayRecordUserListDTO dto);
}