package io.renren.common.modules.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 客服联系方式表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2024-01-18
 */
@Data
@ApiModel(value = "客服联系方式表")
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "客服二维码url链接")
	private String customerQrCodeUrl;

	@ApiModelProperty(value = "客服微信号")
	private String customerWechat;

	@ApiModelProperty(value = "公众号名称")
	private String accountName;

	@ApiModelProperty(value = "联系电话")
	private String telePhone;


}