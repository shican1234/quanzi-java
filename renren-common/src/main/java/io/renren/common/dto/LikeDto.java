package io.renren.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class LikeDto {

    @ApiModelProperty(value = "帖子ID")
    @NotNull(message="帖子ID不能为空")
    private Long tid;

    private Long userId;
}
