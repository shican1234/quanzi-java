package io.renren.common.modules.camiExchange.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 卡密兑换记录表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-16
 */
@Data
@ApiModel(value = "卡密兑换记录表")
public class CamiExchangeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "兑换记录表主键ID")
	private Long id;

	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "卡密号")
	private String camiCode;

	@ApiModelProperty(value = "卡密价格")
	private BigDecimal price;

	@ApiModelProperty(value = "卡密规格(金币为个/VIP为天)")
	private BigDecimal specification;

	@ApiModelProperty(value = "卡密兑换类型(0:VIP  1:金币)")
	private Integer type;

	@ApiModelProperty(value = "卡密表关联ID")
	private Long camiId;

	@ApiModelProperty(value = "兑换时间")
	private Date time;
	/**
	 * 昵称
	 */
	private String memberNichen;
	//用户头像
	private String avatar;

}