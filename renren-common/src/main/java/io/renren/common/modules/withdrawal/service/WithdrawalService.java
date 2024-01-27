package io.renren.common.modules.withdrawal.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.withdrawal.dto.WithdrawalDTO;
import io.renren.common.modules.withdrawal.entity.WithdrawalEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户提现代付表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-16
 */
public interface WithdrawalService extends CrudService<WithdrawalEntity, WithdrawalDTO> {

    /**
     * 根据用户ID查询用户提现记录
     *
     */
    List<WithdrawalEntity> getWithdrawalByUserId(Map<String,Object> map);

}