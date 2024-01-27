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
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "流水查询条件表单")
public class WithdrawalVo {

    @ApiModelProperty(value = "银行名称")
    @NotBlank(message="银行名称不能为空")
    private String bankName;

    @ApiModelProperty(value = "银行卡号")
    @NotBlank(message="银行卡号不能为空")
    private String bankNumber;

    @ApiModelProperty(value = "持卡人真实姓名")
    @NotBlank(message="持卡人真实姓名不能为空")
    private String name;

    @ApiModelProperty(value = "持卡人身份证号")
    @NotBlank(message="持卡人身份证号")
    private String idno;

    @ApiModelProperty(value = "提现金币数量")
    @NotNull(message="提现金额不能为空")
    private BigDecimal money;



}