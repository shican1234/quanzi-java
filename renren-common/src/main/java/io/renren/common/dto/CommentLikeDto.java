package io.renren.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentLikeDto {

    @ApiModelProperty(value = "点赞ID")
    @NotNull(message="点赞ID不能为空")
    private Long commentId;
}
