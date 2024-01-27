package io.renren.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
public class SendCode {
    /* 手机号 */
    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号/邮箱不能为空")
    private String userName;
}
