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

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "注册/登录表单")
public class LoginDTO {
    @ApiModelProperty(value = "邮箱/手机号")
    @NotBlank(message="邮箱/手机号不能为空")
    private String userName;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String passWord;

    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "邀请码")
    private String pid;
    @ApiModelProperty(value = "类型(1登入2注册登入)")
    private Integer loginType;
}