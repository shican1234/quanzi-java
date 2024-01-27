package io.renren.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddFriendDto {

    /* 手机号 */
    @ApiModelProperty(value = "被添加用户")
    @NotNull(message="被添加用户id不能为空")
    private Long toUserId;
    @ApiModelProperty(value = "好友请求消息")
    @NotEmpty(message="好友请求消息不能为空")
    private String  friendMsg;
}
