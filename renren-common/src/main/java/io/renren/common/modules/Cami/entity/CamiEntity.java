package io.renren.common.modules.Cami.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 卡密表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-15
 */
@Data
@TableName("tb_cami")
public class CamiEntity {

    /**
     * 卡密表主键ID
     */
	private Long id;
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
     * 使用状态(0:未使用  1:已使用)
     */
	private Integer status;
    /**
     * 生成时间
     */
	private Date time;
    /**
     * 卡密兑换时间
     */
	private Date exchangeTime;
}