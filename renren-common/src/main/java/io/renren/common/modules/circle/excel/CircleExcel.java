package io.renren.common.modules.circle.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 圈子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Data
public class CircleExcel {
    @Excel(name = "主键ID")
    private Long id;
    @Excel(name = "圈主ID")
    private Long userId;
    @Excel(name = "圈子分类(分类ID)")
    private Long circleId;
    @Excel(name = "圈子介绍")
    private String circleIntroduce;
    @Excel(name = "圈子头像")
    private String circleSculpture;
    @Excel(name = "圈子背景")
    private String circleBackground;
    @Excel(name = "是否付费(0:付费  1:免费)")
    private Integer status;
    @Excel(name = "付费价格")
    private BigDecimal comePrice;
    @Excel(name = "审核状态(0:待审核  1:通过  2:拒绝)")
    private Integer type;
    @Excel(name = "理由(审核通过无需理由,审核拒绝需填写拒绝理由)")
    private String reason;
    @Excel(name = "圈子创建时间")
    private Date time;
    @Excel(name = "是否热门圈子(0不是1是)")
    private Integer hotOk;

}