package io.renren.common.modules.customer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 客服联系方式表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2024-01-18
 */
@Data
@TableName("tb_customer")
public class CustomerEntity {

    /**
     * 主键ID
     */
	private Long id;
    /**
     * 客服二维码url链接
     */
	private String customerQrCodeUrl;
    /**
     * 客服微信号
     */
	private String customerWechat;
    /**
     * 公众号名称
     */
	private String accountName;
    /**
     * 联系电话
     */
    private String telePhone;

}