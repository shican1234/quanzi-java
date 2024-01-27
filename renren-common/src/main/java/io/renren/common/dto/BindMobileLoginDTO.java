/**
 /**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(value = "微信登入后绑定手机账号提交参数")
public class BindMobileLoginDTO {
    @ApiModelProperty(value = "id")
    @NotNull(message="ID错误")
    private Long memberCode;


    @ApiModelProperty(value = "手机号也就是账号")
    @NotNull(message="手机号不能为空")
    private String mobile;

}