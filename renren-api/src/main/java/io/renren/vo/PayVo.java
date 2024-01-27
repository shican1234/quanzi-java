/**
 /**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "支付配置查询条件表单")
public class PayVo {
//
//
//
//
//    @ApiModelProperty(value = "充值商品类型  1:金币  2:会员")
//    @NotNull(message = "充值商品类型不能为空")
//    private Integer typesOf;

    @ApiModelProperty(value = "用户Id")
    private Long userId;

    @ApiModelProperty(value = "用户IP")
    private String ip;

    @ApiModelProperty(value = "支付通道ID")
    private Long payConfigId;

    @ApiModelProperty(value = "商品ID")
    @NotNull(message = "商品不能为空")
    private Long commodityId;

    @ApiModelProperty(value = "支付通道类型")
    @NotNull(message = "支付通道类型不能为空")
    private Integer payType;

    @ApiModelProperty(value = "端别类型 1:APP  2:H5  3:小程序  4:公众号")
    @NotNull(message = "端别类型不能为空")
    private Integer type;


}