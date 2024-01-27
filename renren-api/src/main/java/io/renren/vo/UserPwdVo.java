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
@ApiModel(value = "用户修改密码表单")
public class UserPwdVo {


    @ApiModelProperty(value = "原始密码")
    @NotBlank(message="请输入原始密码")
    private String oldPwd;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message="请输入新密码")
    private String newPwd;

}