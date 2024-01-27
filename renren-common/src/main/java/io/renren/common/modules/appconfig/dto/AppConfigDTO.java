package io.renren.common.modules.appconfig.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * APP设置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-10
 */
@Data
@ApiModel(value = "APP设置")
public class AppConfigDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "APP推广URL")
	private String appTui;

	@ApiModelProperty(value = "是否显示公告 0不显示 1显示")
	private Integer showPopup;

	@ApiModelProperty(value = "公告显示内容")
	private String popupContent;

	@ApiModelProperty(value = "是否显示开屏广告 0不显示 1显示")
	private Integer showAd;

	@ApiModelProperty(value = "开屏广告跳转的url")
	private String adUrl;

	@ApiModelProperty(value = "开屏广告的图片")
	private String adImg;


	/**
	 * 安卓下载地址
	 */
	private String appAndroidUrl;
	/**
	 * IOS下载地址
	 */
	private String appIosUrl;

	/**
	 * arguments
	 */
	private String arguments;
	private String appLogo;
	private String shareTitle;

}