package io.renren.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "用戶修改詳情表單")
public class AccountBinDingVo {

    @ApiModelProperty(value = "类型(1:手机账号  2:邮箱账号)")
    @NotNull(message="绑定类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "绑定账号")
    @NotBlank(message="绑定账号不能为空")
    private String accountNumber;

    @ApiModelProperty(value = "验证码")
    private String code;


}
