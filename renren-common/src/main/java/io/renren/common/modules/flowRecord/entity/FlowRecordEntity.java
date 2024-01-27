package io.renren.common.modules.flowRecord.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 金币流水
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-14
 */
@Data
@TableName("tb_flow_record")
public class FlowRecordEntity {
    @TableId(type = IdType.AUTO)
    /**
     * 主键ID
     */
	private Long id;
    /**
     * 订单编号
     */
    private String code;
    /**
     * 扣除金币数量
     */
	private String amount;
    /**
     * 收入金币数量
     */
    private String income;
    /**
     * 收入用户ID
     */
    private Long awayUserId;
    /**
     * 收入前提现余额
     */
	private String awayFrontBalance;
    /**
     * 收入后提现余额
     */
	private String awayAfterBalance;
    /**
     * 支出用户ID
     */
    private Long outUserId;
    /**
     * 支出前金币余额
     */
    private String outFrontBalance;
    /**
     * 支出后金币余额
     */
    private String outAfterBalance;
    /**
     * 类型(0:打赏 1:付费圈 2:付费贴  3:发红包  4:抢红包  5:红包退回 6签到  7:付费返利  8:赞赏)
     */
    private Integer status;
    /**
     * 通用ID(圈子ID/帖子ID或其他关联ID)
     */
    private Long currentId;
    /**
     * 时间
     */
    private Date time;


    /**
     * 向前端返回收入或扣除的金币数量
     */
    @TableField(exist = false)
    private String number;
}