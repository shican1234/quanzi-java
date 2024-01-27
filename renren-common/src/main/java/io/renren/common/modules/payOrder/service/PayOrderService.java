package io.renren.common.modules.payOrder.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.payOrder.dto.PayOrderDTO;
import io.renren.common.modules.payOrder.entity.PayOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 充值订单表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-13
 */
public interface PayOrderService extends CrudService<PayOrderEntity, PayOrderDTO> {

    /**
     * 根据用户ID查询用户充值成功的订单
     *
     **/
    PayOrderEntity getPayOrderBuOrder(String orderCode);

    /**
     * 根据用户ID查询用户支付成功的充值记录
     *
     **/
    List<PayOrderEntity> getPayOrderByUserId(Map<String,Object> map);

}