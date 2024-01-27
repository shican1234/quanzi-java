package io.renren.common.modules.withdrawal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 用户提现代付表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-16
 */
@Data
@ApiModel(value = "用户提现代付表")
public class WithdrawalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "提现单号")
	private String withdrawalCode;

	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "银行名称")
	private String bankName;

	@ApiModelProperty(value = "银行卡号")
	private String bankNo;

	@ApiModelProperty(value = "真实姓名")
	private String name;

	@ApiModelProperty(value = "身份证号")
	private String idno;

	@ApiModelProperty(value = "提现金币数量")
	private BigDecimal withdrawalSpecies;

	@ApiModelProperty(value = "提现金额(金豆换算的人民币元)")
	private BigDecimal withdrawalMoney;

	@ApiModelProperty(value = "提现实际到账金额(扣除手续费)")
	private BigDecimal actualAccount;

	@ApiModelProperty(value = "提现手续费")
	private BigDecimal serviceCharge;

	@ApiModelProperty(value = "提现手续费比例(%)")
	private BigDecimal serviceChargeScale;

	@ApiModelProperty(value = "提现前的金豆余额")
	private BigDecimal frontSpecies;

	@ApiModelProperty(value = "提现后的金豆余额")
	private BigDecimal behindSpecies;

	@ApiModelProperty(value = "审核状态(0:待审核  1:审核通过  2:审核拒绝)")
	private Integer status;

	@ApiModelProperty(value = "审核内容/代付回调内容(拒绝填写拒绝理由  成功不用填写)")
	private String content;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 昵称
	 */
	private String memberNichen;
	/**
	 * 头像
	 */
	private String avatar;

}