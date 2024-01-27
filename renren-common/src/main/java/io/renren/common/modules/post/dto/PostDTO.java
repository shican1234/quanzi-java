package io.renren.common.modules.post.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 帖子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Data
@ApiModel(value = "帖子表")
public class PostDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "帖子ID")
	private Long id;

	@ApiModelProperty(value = "帖子文字内容")
	private String content;

	@ApiModelProperty(value = "文件地址,json格式")
	private String fileUrls;

	@ApiModelProperty(value = "发帖的人的ID")
	private Long userId;

	@ApiModelProperty(value = "圈子ID")
	private Long circleId;

	@ApiModelProperty(value = "状态(0审核中1审核通过1拒绝)")
	private Integer status;

	@ApiModelProperty(value = "类型(0公开帖子1圈内帖子)")
	private Integer type;

	@ApiModelProperty(value = "发帖时间")
	private Date createTime;

	@ApiModelProperty(value = "文件类型(0图片1视频)")
	private Integer fileType;

	@ApiModelProperty(value = "话题ID")
	private Long discussId;

	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "封面图(多张图片取第一个,视频就是第一帧)")
	private String image;
	@ApiModelProperty(value = "点赞数量")
	private Long dzCount;
	/**
	 * 阅读量
	 */
	private Long readNumer;

	/**
	 * ip
	 */
	private String ip;
	/**
	 * ip位置
	 */
	private String ipLocate;
	/**
	 *帖子类型
	 */
	private Integer postClass;


	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 昵称
	 */
	private String nickName;
	private String circleName;

}