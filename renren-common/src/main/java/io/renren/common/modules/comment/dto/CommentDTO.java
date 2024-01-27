package io.renren.common.modules.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 评论
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-30
 */
@Data
@ApiModel(value = "评论")
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Integer id;

	@ApiModelProperty(value = "所属帖子id")
	private Long tid;

	@ApiModelProperty(value = "评论人id")
	private Long commentUserId;

	@ApiModelProperty(value = "所属评论id，主评论为null")
	private Long parentId;

	@ApiModelProperty(value = "评论内容")
	private String content;


	@ApiModelProperty(value = "状态，0-未审核，1-展现，2-审核驳回")
	private Integer status;

	@ApiModelProperty(value = "")
	private Date createTime;

	@ApiModelProperty(value = "")
	private Date updateTime;

	/**
	 * 点赞数量
	 */
	private Integer likeNum;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 昵称
	 */
	private String nickName;
}