package io.renren.common.modules.appconfig.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * APP设置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-10
 */
@Data
public class AppConfigExcel {
    @Excel(name = "")
    private Long id;
    @Excel(name = "APP推广URL")
    private String appTui;
    @Excel(name = "是否显示公告 0不显示 1显示")
    private Integer showPopup;
    @Excel(name = "公告显示内容")
    private String popupContent;
    @Excel(name = "是否显示开屏广告 0不显示 1显示")
    private Integer showAd;
    @Excel(name = "开屏广告跳转的url")
    private String adUrl;
    @Excel(name = "开屏广告的图片")
    private String adImg;
    @Excel(name = "网站名字")
    private String appName;

}