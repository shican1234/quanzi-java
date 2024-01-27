package io.renren.common.modules.withdrawal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.withdrawal.dao.WithdrawalDao;
import io.renren.common.modules.withdrawal.dto.WithdrawalDTO;
import io.renren.common.modules.withdrawal.entity.WithdrawalEntity;
import io.renren.common.modules.withdrawal.service.WithdrawalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户提现代付表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-16
 */
@Service
public class WithdrawalServiceImpl extends CrudServiceImpl<WithdrawalDao, WithdrawalEntity, WithdrawalDTO> implements WithdrawalService {

    @Autowired
    private WithdrawalDao withdrawalDao;

    @Override
    public QueryWrapper<WithdrawalEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String userId = (String)params.get("userId");
        String withdrawalCode = (String)params.get("withdrawalCode");
        String bankNo = (String)params.get("bankNo");
        String name = (String)params.get("name");
        String idno = (String)params.get("idno");
        String status = (String)params.get("status");

        QueryWrapper<WithdrawalEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(userId), "user_id", userId);
        wrapper.eq(StringUtils.isNotBlank(withdrawalCode), "withdrawal_code", withdrawalCode);
        wrapper.like(StringUtils.isNotBlank(bankNo), "bank_no", bankNo);
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.like(StringUtils.isNotBlank(idno), "idno", idno);
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);


        wrapper.orderByDesc("create_time");

        return wrapper;
    }


    @Override
    public List<WithdrawalEntity> getWithdrawalByUserId(Map<String, Object> map) {


        return withdrawalDao.getWithdrawalByUserId(map);
    }
}