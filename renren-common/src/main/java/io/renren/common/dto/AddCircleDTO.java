package io.renren.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 圈子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Data
@ApiModel(value = "圈子表")
public class AddCircleDTO implements Serializable {
    private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "圈主ID")
	private Long userId;

	@ApiModelProperty(value = "圈子分类(分类ID)")
	@NotNull(message="请选择圈子分类")
	private Long circleClassId;

	@ApiModelProperty(value = "圈子介绍")
	@NotEmpty(message="请填写圈子介绍")
	@Length(min = 10, max = 255, message = "介绍内容必须在{min}和{max}之间")
	private String circleIntroduce;

	@ApiModelProperty(value = "圈子头像")
	@NotEmpty(message="请上传圈子头像")
	private String circleSculpture;

	@ApiModelProperty(value = "圈子背景")
	@NotEmpty(message="请上传圈子背景")
	private String circleBackground;

	@ApiModelProperty(value = "是否付费(0:否  1:是)")
	private Integer type;
	@ApiModelProperty(value = "是否私密(0:否  1:是)")
	private Integer privacyType;

	@ApiModelProperty(value = "付费价格")
	private BigDecimal comePrice;

	@ApiModelProperty(value = "审核状态(0:待审核  1:通过  2:拒绝)")
	private Integer status;

	@ApiModelProperty(value = "理由(审核通过无需理由,审核拒绝需填写拒绝理由)")
	private String reason;

	@ApiModelProperty(value = "圈子创建时间")
	private Date time;

	@ApiModelProperty(value = "是否热门圈子(0不是1是)")
	private Integer hotOk;
	@ApiModelProperty(value = "圈子名称")
	@NotEmpty(message="请填写圈子名称")
	@Length(min = 2, max = 8, message = "圈子名称必须在{min}和{max}之间")
	private String circleName;


}