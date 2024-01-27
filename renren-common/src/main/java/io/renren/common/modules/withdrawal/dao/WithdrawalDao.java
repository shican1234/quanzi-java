package io.renren.common.modules.withdrawal.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.flowRecord.entity.FlowRecordEntity;
import io.renren.common.modules.withdrawal.entity.WithdrawalEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户提现代付表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-16
 */
@Mapper
public interface WithdrawalDao extends BaseDao<WithdrawalEntity> {

    /**
     * 根据用户ID查询用户提现记录
     *
     */
    List<WithdrawalEntity> getWithdrawalByUserId(Map<String,Object> map);
	
}