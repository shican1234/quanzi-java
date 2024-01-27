package io.renren.common.modules.circleuser.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 圈子人员
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-08
 */
@Data
@ApiModel(value = "圈子人员")
public class CircleUserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "圈子ID")
	private Long circleId;

	@ApiModelProperty(value = "用户ID")
	private Long userId;

	@ApiModelProperty(value = "入圈时间")
	private Date createTime;

	@ApiModelProperty(value = "类型(0普通用户1特邀嘉宾2管理员3圈主)")
	private Integer type;

	@ApiModelProperty(value = "用户名")
	private String nickName;
	@ApiModelProperty(value = "头像")
	private String avatar;
	@ApiModelProperty(value = "性别")
	private Integer gender;

	private String circleName;

}