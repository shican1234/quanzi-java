package io.renren.common.modules.userfeedback.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.userfeedback.dto.UserFeedbackDTO;
import io.renren.common.modules.userfeedback.entity.UserFeedbackEntity;

import java.util.List;

/**
 * 
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-20
 */
public interface UserFeedbackService extends CrudService<UserFeedbackEntity, UserFeedbackDTO> {

    /**
     * 根据用户ID查询用户的反馈信息
     *
     */
    List<UserFeedbackEntity> getFlowRecordByUserId(Long userId);

}