package io.renren.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddCommentDto {

    @ApiModelProperty(value = "帖子ID")
    @NotNull(message="帖子ID不能为空")
    private Long tid;
    @ApiModelProperty(value = "评论内容")
    @NotBlank(message="评论内容不能为空")
    private String content;
    private Long pid;
}
