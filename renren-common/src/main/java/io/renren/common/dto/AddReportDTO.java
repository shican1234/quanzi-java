package io.renren.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 举报表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-21
 */
@Data
@ApiModel(value = "举报表")
public class AddReportDTO implements Serializable {
    private static final long serialVersionUID = 1L;



	@ApiModelProperty(value = "举报原因")
	@NotEmpty(message = "请选择举报原由")
	private String reasons;
	@ApiModelProperty(value = "举报内容")
	@NotEmpty(message = "请填写举报内容")
	private String content;

	@ApiModelProperty(value = "文件url")
	@NotEmpty(message="请最少上传最少一张截图")
	private List<String> fileUrls;


	@ApiModelProperty(value = "被举报的帖子ID")
	@NotNull(message="被举报帖子ID错误")
	private Long postId;


}