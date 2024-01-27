package io.renren.common.modules.userfeedback.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-20
 */
@Data
@ApiModel(value = "")
public class UserFeedbackDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "反馈用户ID")
	private Long userId;

	@ApiModelProperty(value = "联系名称")
	private String userName;

	@ApiModelProperty(value = "联系方式")
	private String contactInformation;

	@ApiModelProperty(value = "反馈图片")
	private String img;

	@ApiModelProperty(value = "反馈内容")
	private String feedbackContent;

	@ApiModelProperty(value = "回复内容")
	private String replyContent;

	@ApiModelProperty(value = "回复状态(0:未回复  1:已回复)")
	private Integer type;

	@ApiModelProperty(value = "是否解决(0:为解决 1:已解决)")
	private Integer status;

	@ApiModelProperty(value = "反馈时间")
	private Date feedbackTime;

	@ApiModelProperty(value = "回复时间")
	private Date replyTime;

	/**
	 * 昵称
	 */
	private String memberNichen;
	/**
	 * 头像
	 */
	private String avatar;

	@ApiModelProperty(value = "反馈图片集合")
	private List<String> images;

}