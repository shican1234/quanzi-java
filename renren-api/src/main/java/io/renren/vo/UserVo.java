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
import java.math.BigDecimal;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "用户修改数据表单")
public class UserVo {


    @ApiModelProperty(value = "用户头像")
    @NotBlank(message="请上传用户头像")
    private String memberAvatar;

    @ApiModelProperty(value = "用户昵称")
    @NotBlank(message="请填写用户昵称")
    private String memberNichen;

}