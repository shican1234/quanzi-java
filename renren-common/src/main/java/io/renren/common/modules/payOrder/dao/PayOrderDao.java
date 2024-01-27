package io.renren.common.modules.payOrder.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.payOrder.entity.PayOrderEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 充值订单表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-13
 */
@Mapper
public interface PayOrderDao extends BaseDao<PayOrderEntity> {

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