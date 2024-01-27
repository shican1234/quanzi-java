package io.renren.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
@ApiModel(value = "修改圈子")
public class UpdateCircleDTO implements Serializable {
    private static final long serialVersionUID = 1L;


	private Long id;

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
	@ApiModelProperty(value = "圈子名称")
	@NotEmpty(message="请填写圈子名称")
	@Length(min = 2, max = 8, message = "圈子名称必须在{min}和{max}之间")
	private String circleName;


}