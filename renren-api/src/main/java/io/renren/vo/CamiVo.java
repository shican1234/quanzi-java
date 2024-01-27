package io.renren.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CamiVo {

    @ApiModelProperty(value = "卡密号")
    @NotBlank(message = "卡密号不能为空")
    private String camiCode;

}
