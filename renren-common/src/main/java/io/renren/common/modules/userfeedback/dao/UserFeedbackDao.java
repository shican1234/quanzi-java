package io.renren.common.modules.userfeedback.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.userfeedback.entity.UserFeedbackEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-20
 */
@Mapper
public interface UserFeedbackDao extends BaseDao<UserFeedbackEntity> {

    /**
     * 根据用户ID查询用户的反馈信息
     *
     */
	List<UserFeedbackEntity> getFlowRecordByUserId(Long userId);
}