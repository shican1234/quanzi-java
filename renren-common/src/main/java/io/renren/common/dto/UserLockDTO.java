package io.renren.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-07-04
 */
@Data
@ApiModel(value = "用户")
public class UserLockDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long userId;


	@ApiModelProperty(value = "用户状态(0正常1封禁)")
	private Integer status;

	@ApiModelProperty(value = "封禁原因")
	private String lockReason;


	/**
	 * 私聊文字聊天单价
	 */
	private Date unLockTime;

}