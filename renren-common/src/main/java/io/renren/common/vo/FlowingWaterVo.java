/**
 /**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "流水查询条件表单")
public class FlowingWaterVo {


//    @ApiModelProperty(value = "分页数据")
//    private Integer page;
//
//    @ApiModelProperty(value = "下标")
//    private Integer index;
//
//    @ApiModelProperty(value = "订单号")
//    private String title;
//
//    @ApiModelProperty(value = "金额")
//    private String price;
//
//    @ApiModelProperty(value = "id")
//    private Long id;
//
//    @ApiModelProperty(value = "来源(返利,消耗)")
//    private String amountSource;

    @ApiModelProperty(value = "审核状态(0:待审核  1:审核通过  2:审核拒绝)")
    private Integer status;

}