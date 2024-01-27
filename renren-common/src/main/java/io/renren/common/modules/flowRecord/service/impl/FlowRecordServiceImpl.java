package io.renren.common.modules.flowRecord.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.dto.PayRecordUserListDTO;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.flowRecord.dao.FlowRecordDao;
import io.renren.common.modules.flowRecord.dto.FlowRecordDTO;
import io.renren.common.modules.flowRecord.entity.FlowRecordEntity;
import io.renren.common.modules.flowRecord.service.FlowRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 金币流水
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-14
 */
@Service
public class FlowRecordServiceImpl extends CrudServiceImpl<FlowRecordDao, FlowRecordEntity, FlowRecordDTO> implements FlowRecordService {

    @Autowired
    private FlowRecordDao flowRecordDao;

    @Override
    public QueryWrapper<FlowRecordEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String code = (String)params.get("code");
        String awayUserId = (String)params.get("awayUserId");
        String outUserId = (String)params.get("outUserId");

        QueryWrapper<FlowRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(code), "code", code);
        wrapper.eq(StringUtils.isNotBlank(awayUserId), "away_user_id", awayUserId);
        wrapper.eq(StringUtils.isNotBlank(outUserId), "out_user_id", outUserId);

        wrapper.orderByDesc("time");

        return wrapper;
    }


    @Override
    public List<FlowRecordEntity> getFlowRecordByUserId(Map<String, Object> map) {
        return flowRecordDao.getFlowRecordByUserId(map);
    }

    @Override
    public FlowRecordEntity selectEntityByResIdAndStatusAndUserId(Long awayUserId,Long outUserId, int status) {
        QueryWrapper<FlowRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FlowRecordEntity::getStatus,status);
        wrapper.lambda().eq(FlowRecordEntity::getAwayUserId,awayUserId);
        wrapper.lambda().eq(FlowRecordEntity::getOutUserId,outUserId);
        return baseDao.selectOne(wrapper);
    }

    @Override
    public List<FlowRecordEntity> selectEntityByOutUserIdAndStatusAndTid(Long id, int status,Long tid) {
        QueryWrapper<FlowRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FlowRecordEntity::getStatus,status);
        wrapper.lambda().eq(FlowRecordEntity::getOutUserId,id);
        wrapper.lambda().eq(FlowRecordEntity::getCurrentId,tid);
        return baseDao.selectList(wrapper);
    }

    @Override
    public List<UserEntity> payRecordUserList(PayRecordUserListDTO dto) {
        return baseDao.payRecordUserList(dto);
    }
}