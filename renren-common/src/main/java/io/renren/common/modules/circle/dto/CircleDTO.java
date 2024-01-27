package io.renren.common.modules.circle.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 圈子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Data
@ApiModel(value = "圈子表")
public class CircleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "圈主ID")
	private Long userId;

	@ApiModelProperty(value = "圈子分类(分类ID)")
	private Long circleClassId;

	@ApiModelProperty(value = "圈子介绍")
	private String circleIntroduce;

	@ApiModelProperty(value = "圈子头像")
	private String circleSculpture;

	@ApiModelProperty(value = "圈子背景")
	private String circleBackground;



	@ApiModelProperty(value = "审核状态(0:待审核  1:通过  2:拒绝)")
	private Integer status;

	@ApiModelProperty(value = "理由(审核通过无需理由,审核拒绝需填写拒绝理由)")
	private String reason;

	@ApiModelProperty(value = "圈子创建时间")
	private Date time;

	@ApiModelProperty(value = "是否热门圈子(0不是1是)")
	private Integer hotOk;

	@ApiModelProperty(value = "圈子名称")
	private String circleName;

	private String circleClassName;
	private String nickName;
	private String avatar;


}