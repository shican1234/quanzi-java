package io.renren.common.modules.withdrawal.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户提现代付表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-16
 */
@Data
public class WithdrawalExcel {
    @Excel(name = "主键ID")
    private Long id;
    @Excel(name = "提现单号")
    private String withdrawalCode;
    @Excel(name = "用户ID")
    private Long userId;
    @Excel(name = "提现金币数量")
    private BigDecimal withdrawalSpecies;
    @Excel(name = "提现金额(金币换算的人民币元)")
    private BigDecimal withdrawalMoney;
    @Excel(name = "提现前的金豆余额")
    private BigDecimal frontSpecies;
    @Excel(name = "提现后的金豆余额")
    private BigDecimal behindSpecies;
    @Excel(name = "收款类型( 1:支付宝  2:微信)")
    private Integer type;
    @Excel(name = "审核状态(0:待审核  1:审核通过  2:审核拒绝)")
    private Integer status;
    @Excel(name = "审核内容/代付回调内容(拒绝填写拒绝理由  成功不用填写)")
    private String content;
    @Excel(name = "创建时间")
    private Date createTime;

}