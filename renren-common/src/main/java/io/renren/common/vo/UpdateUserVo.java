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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "用戶修改詳情表單")
public class UpdateUserVo {

    @ApiModelProperty(value = "用户昵称")
    @NotBlank(message="用户昵称不能为空")
    private String nickName;

    @ApiModelProperty(value = "婚姻状态")
    @NotEmpty(message="婚姻状态不能为空")
    private String emotion;

    @ApiModelProperty(value = "职位")
    @NotEmpty(message="职位不能为空")
    private String job;

    @ApiModelProperty(value = "城市")
    @NotBlank(message="城市不能为空")
    private String city;

    @ApiModelProperty(value = "性别")
    @NotNull(message="性别不能为空")
    private Integer gender;

    @ApiModelProperty(value = "生日")
    @NotBlank(message="生日不能为空")
    private String birth;

    @ApiModelProperty(value = "头像")
    @NotNull(message="头像不能为空")
    private String avatar;

    @ApiModelProperty(value = "个人主页背景")
    private String bgImg;
    @ApiModelProperty(value = "个性签名")
    private String signature;

}