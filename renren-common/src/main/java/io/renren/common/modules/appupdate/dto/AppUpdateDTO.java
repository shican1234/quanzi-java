package io.renren.common.modules.appupdate.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 更新配置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-20
 */
@Data
@ApiModel(value = "更新配置")
public class AppUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "版本号")
	private String version;

	@ApiModelProperty(value = "更新内容")
	private String note;

	@ApiModelProperty(value = "更新地址")
	private String downloadUrl;

	@ApiModelProperty(value = "添加时间")
	private Date createTime;

	@ApiModelProperty(value = "平台")
	private String os;
	private Integer forces;

}