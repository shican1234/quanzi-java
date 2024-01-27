package io.renren.common.modules.commodity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tb_commodity")
public class CommodityEntity {
    @TableId(type = IdType.AUTO)
    /**
     * 商品表主键ID
     */
	private Long id;
    /**
     * 显示名称(1个  2个   3个)
     */
	private String duration;
    /**
     * 商品价格
     */
	private BigDecimal price;
    /**
     * 商品规格(金币为个  会员为天或月)
     */
	private String commoditySpecification;
    /**
     * 商品排序
     */
	private Integer commodityToSort;
    /**
     * 商品类型(1:钻石  2:VIP)
     */
	private Integer type;
    /**
     * 是否开启(1:是  2:否)
     */
	private Integer status;
    /**
     * 标签开启(0:开启  1:关闭)
     */
    private Integer label;
    /**
     * 商品简介(例如优惠,特惠等字数较少的介绍)
     */
    private String briefIntroduction;
    /**
     * 创建时间
     */
	private Date time;
}