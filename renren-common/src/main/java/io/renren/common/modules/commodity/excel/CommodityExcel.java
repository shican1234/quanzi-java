package io.renren.common.modules.commodity.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-11
 */
@Data
public class CommodityExcel {
    @Excel(name = "商品表主键ID")
    private Long id;
    @Excel(name = "显示名称(1个  2个   3个)")
    private String duration;
    @Excel(name = "商品价格")
    private BigDecimal price;
    @Excel(name = "商品规格(金币为个  会员为天或月)")
    private String commoditySpecification;
    @Excel(name = "商品排序")
    private Integer commodityToSort;
    @Excel(name = "商品类型(1:钻石  2:VIP)")
    private Integer type;
    @Excel(name = "是否开启(1:是  2:否)")
    private Integer status;
    @Excel(name = "创建时间")
    private Date time;

}