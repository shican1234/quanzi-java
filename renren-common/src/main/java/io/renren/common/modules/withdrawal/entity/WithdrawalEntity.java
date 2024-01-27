package io.renren.common.modules.withdrawal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("tb_withdrawal")
public class WithdrawalEntity {
    @TableId(type = IdType.AUTO)
    /**
     * 主键ID
     */
	private Long id;
    /**
     * 提现单号
     */
	private String withdrawalCode;
    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行卡号
     */
    private String bankNo;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 身份证号
     */
    private String idno;
    /**
     * 提现金币数量
     */
	private BigDecimal withdrawalSpecies;
    /**
     * 提现金额(金币换算的人民币元)
     */
	private BigDecimal withdrawalMoney;
    /**
     * 提现实际到账金额(扣除手续费)
     */
    private BigDecimal actualAccount;
    /**
     * 提现手续费
     */
    private BigDecimal serviceCharge;
    /**
     * 提现手续费比例(%)
     */
    private BigDecimal serviceChargeScale;

    /**
     * 提现前的金豆余额
     */
	private BigDecimal frontSpecies;
    /**
     * 提现后的金豆余额
     */
	private BigDecimal behindSpecies;
    /**
     * 审核状态(0:待审核  1:审核通过  2:审核拒绝)
     */
	private Integer status;
    /**
     * 审核内容/代付回调内容(拒绝填写拒绝理由  成功不用填写)
     */
	private String content;
    /**
     * 创建时间
     */
	private Date createTime;
}