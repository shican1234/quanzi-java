package io.renren.common.modules.camiExchange.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class CamiExchangeExcel {
    @Excel(name = "兑换记录表主键ID")
    private Long id;
    @Excel(name = "用户ID")
    private Long userId;
    @Excel(name = "卡密号")
    private String camiCode;
    @Excel(name = "卡密价格")
    private BigDecimal price;
    @Excel(name = "卡密规格(金币为个/VIP为天)")
    private BigDecimal specification;
    @Excel(name = "卡密兑换类型(0:VIP  1:金币)")
    private Integer type;
    @Excel(name = "卡密表关联ID")
    private Long camiId;
    @Excel(name = "兑换时间")
    private Date time;

}