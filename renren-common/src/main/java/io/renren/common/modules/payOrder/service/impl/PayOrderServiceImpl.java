package io.renren.common.modules.payOrder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.payOrder.dao.PayOrderDao;
import io.renren.common.modules.payOrder.dto.PayOrderDTO;
import io.renren.common.modules.payOrder.entity.PayOrderEntity;
import io.renren.common.modules.payOrder.service.PayOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 充值订单表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-13
 */
@Service
public class PayOrderServiceImpl extends CrudServiceImpl<PayOrderDao, PayOrderEntity, PayOrderDTO> implements PayOrderService {

    @Autowired
    private PayOrderDao payOrderDao;
    @Override
    public QueryWrapper<PayOrderEntity> getWrapper(Map<String, Object> params){
        //拿到查询条件
        String id = (String)params.get("id");
        String userId = (String)params.get("userId");
        String peyType = (String)params.get("peyType");
        String peyStatus = (String)params.get("peyStatus");
        String commodityType = (String)params.get("commodityType");
        String orderCode = (String)params.get("orderCode");
        String trilateralCode = (String)params.get("trilateralCode");

        QueryWrapper<PayOrderEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(userId), "user_id", userId);
        wrapper.eq(StringUtils.isNotBlank(peyType), "pey_type", peyType);
        wrapper.eq(StringUtils.isNotBlank(peyStatus), "pey_status", peyStatus);
        wrapper.eq(StringUtils.isNotBlank(commodityType), "commodity_type", commodityType);
        wrapper.eq(StringUtils.isNotBlank(orderCode), "order_code", orderCode);
        wrapper.eq(StringUtils.isNotBlank(trilateralCode), "trilateral_code", trilateralCode);

        wrapper.orderByDesc("found_time");

        return wrapper;
    }


    @Override
    public PayOrderEntity getPayOrderBuOrder(String orderCode) {


        return payOrderDao.getPayOrderBuOrder(orderCode);
    }

    @Override
    public List<PayOrderEntity> getPayOrderByUserId(Map<String, Object> map) {


        return payOrderDao.getPayOrderByUserId(map);
    }
}