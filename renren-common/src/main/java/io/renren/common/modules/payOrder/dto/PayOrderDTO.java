package io.renren.common.modules.payOrder.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 充值订单表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-13
 */
@Data
@ApiModel(value = "充值订单表")
public class PayOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "充值用户昵称")
	private String userName;

	@ApiModelProperty(value = "充值商品名称")
	private String commodityName;

	@ApiModelProperty(value = "充值商品类型(1:钻石  2:VIP)")
	private Integer commodityType;

	@ApiModelProperty(value = "商品规格(钻石是数量  VIP是天数)")
	private String commoditySpecification;

	@ApiModelProperty(value = "支付类型(1:支付宝  2:微信)")
	private Integer peyType;

	@ApiModelProperty(value = "拉起方式(1:APP  2:H5  3:小程序  4:公众号)")
	private Integer peyStatus;

	@ApiModelProperty(value = "平台订单号")
	private String orderCode;

	@ApiModelProperty(value = "三方支付订单号(查账订单号)")
	private String trilateralCode;

	@ApiModelProperty(value = "充值金额(元)")
	private BigDecimal rechargeAmount;

	@ApiModelProperty(value = "订单支付状态(0:订单创建  1:支付成功  2:支付失败  3:订单超时)")
	private Integer orderType;

	@ApiModelProperty(value = "订单创建时间")
	private Date foundTime;

	/**
	 * 昵称
	 */
	private String memberNichen;
	/**
	 * 头像
	 */
	private String avatar;


}