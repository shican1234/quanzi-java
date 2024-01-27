package io.renren.common.modules.follow.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 关注表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-01
 */
@Data
@ApiModel(value = "关注表")
public class FollowDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "关注用户id")
	private Long fromUser;

	@ApiModelProperty(value = "被关注用户id")
	private Long toUser;

	@ApiModelProperty(value = "关注时间")
	private Date createTime;
	private String fromName;
	private String fromAvatar;
	private String toName;
	private String toAvatar;


}