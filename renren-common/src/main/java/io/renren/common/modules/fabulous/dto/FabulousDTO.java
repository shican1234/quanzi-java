package io.renren.common.modules.fabulous.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 点赞记录表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Data
@ApiModel(value = "点赞记录表")
public class FabulousDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键id")
	private Long id;

	@ApiModelProperty(value = "动态的id")
	private Long tid;

	@ApiModelProperty(value = "点赞的人的id")
	private Long userId;

	@ApiModelProperty(value = "点赞的时间")
	private Date createTime;

	/**
	 * 类型(0帖子1评论)
	 */
	private Integer type;
}