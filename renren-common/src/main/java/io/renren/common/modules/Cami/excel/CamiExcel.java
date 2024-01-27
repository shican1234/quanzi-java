package io.renren.common.modules.Cami.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class CamiExcel {
    @Excel(name = "卡密表主键ID")
    private Integer id;
    @Excel(name = "卡密号")
    private String camiCode;
    @Excel(name = "卡密价格")
    private BigDecimal price;
    @Excel(name = "卡密规格(金币为个/VIP为天)")
    private BigDecimal specification;
    @Excel(name = "卡密兑换类型(0:VIP  1:金币)")
    private Integer type;
    @Excel(name = "使用状态(0:未使用  1:已使用)")
    private Integer status;
    @Excel(name = "生成时间")
    private Date time;
    @Excel(name = "卡密兑换时间")
    private Date exchangeTime;

}