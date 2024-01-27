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

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "支付配置查询条件表单")
public class PayConFigVo {


    @ApiModelProperty(value = "端别类型 1:APP  2:H5  3:小程序")
    @NotBlank(message = "端别类型不能为空")
    private Integer type;

}