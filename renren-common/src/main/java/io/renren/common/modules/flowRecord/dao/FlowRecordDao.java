package io.renren.common.modules.flowRecord.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.dto.PayRecordUserListDTO;
import io.renren.common.modules.flowRecord.entity.FlowRecordEntity;
import io.renren.common.modules.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 金币流水
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-14
 */
@Mapper
public interface FlowRecordDao extends BaseDao<FlowRecordEntity> {

    /**
     * 根据用户ID查询用户的金币流水数据
     *
     */
	List<FlowRecordEntity> getFlowRecordByUserId(Map<String,Object> map);

    List<UserEntity> payRecordUserList(PayRecordUserListDTO dto);
}