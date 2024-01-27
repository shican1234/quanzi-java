package io.renren.common.modules.Cami.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 卡密表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-15
 */
@Data
@ApiModel(value = "卡密表")
public class CamiDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "卡密表主键ID")
	private Long id;

	@ApiModelProperty(value = "卡密号")
	private String camiCode;

	@ApiModelProperty(value = "卡密价格")
	private BigDecimal price;

	@ApiModelProperty(value = "卡密规格(金币为个/VIP为天)")
	private BigDecimal specification;

	@ApiModelProperty(value = "卡密兑换类型(0:VIP  1:金币)")
	private Integer type;

	@ApiModelProperty(value = "使用状态(0:未使用  1:已使用)")
	private Integer status;

	@ApiModelProperty(value = "生成时间")
	private Date time;

	@ApiModelProperty(value = "卡密兑换时间")
	private Date exchangeTime;

	@ApiModelProperty(value = "生成数量")
	private Integer number;


}