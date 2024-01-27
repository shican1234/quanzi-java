package io.renren.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class FollowApiDto {

    /* 手机号 */
    @ApiModelProperty(value = "被关注的人")
    @NotNull(message="被关注用户id不能为空")
    private Long toUserId;
}
