package io.renren.common.modules.camiExchange.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 卡密兑换记录表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-16
 */
@Data
@TableName("tb_cami_exchange")
public class CamiExchangeEntity {

    /**
     * 兑换记录表主键ID
     */
	private Long id;
    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 卡密号
     */
	private String camiCode;
    /**
     * 卡密价格
     */
	private BigDecimal price;
    /**
     * 卡密规格(金币为个/VIP为天)
     */
	private BigDecimal specification;
    /**
     * 卡密兑换类型(0:VIP  1:金币)
     */
	private Integer type;
    /**
     * 卡密表关联ID
     */
	private Long camiId;
    /**
     * 兑换时间
     */
	private Date time;
}