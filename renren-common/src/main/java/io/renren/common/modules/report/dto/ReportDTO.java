package io.renren.common.modules.report.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 举报表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-21
 */
@Data
@ApiModel(value = "举报表")
public class ReportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Long id;

	@ApiModelProperty(value = "处理进度(0待处理1已处理2已关闭)")
	private Integer status;

	@ApiModelProperty(value = "举报者Id")
	private Long fromUser;

	@ApiModelProperty(value = "被举报者ID")
	private Long toUser;

	@ApiModelProperty(value = "举报原因(0:诱导欺骗送礼物1诱导去其他平台2聊天内容涉黄3诱导涉黄4语言暴力/骚扰)")
	private String reasons;

	@ApiModelProperty(value = "举报时间")
	private Date createTime;

	@ApiModelProperty(value = "举报截屏的URL,多条以^分割")
	private String screenShot;

	@ApiModelProperty(value = "被举报的帖子ID")
	private Long postId;

	@ApiModelProperty(value = "被举报的圈子ID")
	private Long circleId;
	/**
	 * 举报内容
	 */
	private String content;

	private String fromName;
	private String fromAvatar;
	private String toAvatar;
	private String toName;

}