package io.renren.common.modules.customer.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 客服联系方式表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2024-01-18
 */
@Data
public class CustomerExcel {
    @Excel(name = "主键ID")
    private Long id;
    @Excel(name = "客服二维码url链接")
    private String 
customerQrCodeUrl;
    @Excel(name = "客服微信号")
    private String customerWechat;
    @Excel(name = "公众号名称")
    private String accountName;

}