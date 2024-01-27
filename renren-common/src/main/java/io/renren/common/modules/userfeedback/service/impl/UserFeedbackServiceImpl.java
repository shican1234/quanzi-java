package io.renren.common.modules.userfeedback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.userfeedback.dao.UserFeedbackDao;
import io.renren.common.modules.userfeedback.dto.UserFeedbackDTO;
import io.renren.common.modules.userfeedback.entity.UserFeedbackEntity;
import io.renren.common.modules.userfeedback.service.UserFeedbackService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-20
 */
@Service
public class UserFeedbackServiceImpl extends CrudServiceImpl<UserFeedbackDao, UserFeedbackEntity, UserFeedbackDTO> implements UserFeedbackService {

    @Autowired
    private UserFeedbackDao userFeedbackDao;

    @Override
    public QueryWrapper<UserFeedbackEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<UserFeedbackEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        wrapper.orderByDesc("feedback_time");

        return wrapper;
    }


    @Override
    public List<UserFeedbackEntity> getFlowRecordByUserId(Long userId) {

        List<UserFeedbackEntity> flowRecordByUserId = userFeedbackDao.getFlowRecordByUserId(userId);

        return flowRecordByUserId;
    }
}