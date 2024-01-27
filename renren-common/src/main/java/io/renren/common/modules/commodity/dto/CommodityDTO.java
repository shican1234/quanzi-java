package io.renren.common.modules.commodity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 商品表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-11
 */
@Data
@ApiModel(value = "商品表")
public class CommodityDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "商品表主键ID")
	private Long id;

	@ApiModelProperty(value = "显示名称(1个  2个   3个)")
	private String duration;

	@ApiModelProperty(value = "商品价格")
	private BigDecimal price;

	@ApiModelProperty(value = "商品规格(金币为个  会员为天或月)")
	private String commoditySpecification;

	@ApiModelProperty(value = "商品排序")
	private Integer commodityToSort;

	@ApiModelProperty(value = "商品类型(1:钻石  2:VIP)")
	private Integer type;

	@ApiModelProperty(value = "是否开启(1:是  2:否)")
	private Integer status;

	@ApiModelProperty(value = "标签开启(0:开启  1:关闭)")
	private Integer label;

	@ApiModelProperty(value = "商品简介(例如优惠,特惠等字数较少的介绍)")
	private String briefIntroduction;

	@ApiModelProperty(value = "创建时间")
	private Date time;


}