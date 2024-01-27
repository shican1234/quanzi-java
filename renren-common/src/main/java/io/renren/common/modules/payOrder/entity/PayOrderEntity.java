package io.renren.common.modules.payOrder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值订单表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-13
 */
@Data
@TableName("tb_pay_order")
public class PayOrderEntity {
    @TableId(type = IdType.AUTO)
    /**
     * 主键ID
     */
	private Long id;
    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 充值用户昵称
     */
	private String userName;
    /**
     * 充值商品名称
     */
	private String commodityName;
    /**
     * 充值商品类型(1:钻石  2:VIP)
     */
	private Integer commodityType;
    /**
     * 商品规格(钻石是数量  VIP是天数)
     */
	private String commoditySpecification;
    /**
     * 支付类型(1:支付宝  2:微信)
     */
	private Integer peyType;
    /**
     * 拉起方式(1:APP  2:H5  3:小程序  4:公众号)
     */
	private Integer peyStatus;
    /**
     * 平台订单号
     */
	private String orderCode;
    /**
     * 三方支付订单号(查账订单号)
     */
	private String trilateralCode;
    /**
     * 充值金额(元)
     */
	private BigDecimal rechargeAmount;
    /**
     * 订单支付状态(0:订单创建  1:支付成功  2:支付失败  3:订单超时)
     */
	private Integer orderType;
    /**
     * 订单创建时间
     */
	private Date foundTime;
}