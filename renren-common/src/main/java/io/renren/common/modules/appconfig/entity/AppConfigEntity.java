package io.renren.common.modules.appconfig.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * APP设置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-10
 */
@Data
@TableName("tb_app_config")
public class AppConfigEntity {
    @TableId(type = IdType.AUTO)
    /**
     * 
     */
	private Long id;
    /**
     * APP推广URL
     */
	private String appTui;
    /**
     * 是否显示公告 0不显示 1显示
     */
	private Integer showPopup;
    /**
     * 公告显示内容
     */
	private String popupContent;
    /**
     * 是否显示开屏广告 0不显示 1显示
     */
	private Integer showAd;
    /**
     * 开屏广告跳转的url
     */
	private String adUrl;
    /**
     * 开屏广告的图片
     */
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