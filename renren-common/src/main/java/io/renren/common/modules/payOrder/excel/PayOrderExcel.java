package io.renren.common.modules.payOrder.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值订单表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-13
 */
@Data
public class PayOrderExcel {
    @Excel(name = "主键ID")
    private Long id;
    @Excel(name = "用户ID")
    private Long userId;
    @Excel(name = "充值用户昵称")
    private String userName;
    @Excel(name = "充值商品名称")
    private String commodityName;
    @Excel(name = "充值商品类型(1:钻石  2:VIP)")
    private Integer commodityType;
    @Excel(name = "商品规格(钻石是数量  VIP是天数)")
    private String commoditySpecification;
    @Excel(name = "支付类型(1:支付宝  2:微信)")
    private Integer peyType;
    @Excel(name = "拉起方式(1:APP  2:H5  3:小程序)")
    private Integer peyStatus;
    @Excel(name = "平台订单号")
    private String orderCode;
    @Excel(name = "三方支付订单号(查账订单号)")
    private String trilateralCode;
    @Excel(name = "充值金额(元)")
    private BigDecimal rechargeAmount;
    @Excel(name = "订单支付状态(0:订单创建  1:支付成功  2:支付失败  3:订单超时)")
    private Integer orderType;
    @Excel(name = "订单创建时间")
    private Date foundTime;

}