package io.renren.common.modules.flowRecord.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 金币流水
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-14
 */
@Data
public class FlowRecordExcel {
    @Excel(name = "主键ID")
    private Long id;
    @Excel(name = "用户ID")
    private Long userId;
    @Excel(name = "金币数量")
    private String amount;
    @Excel(name = "前金币数量")
    private String frontBalance;
    @Excel(name = "后金币数量")
    private String afterBalance;
    @Excel(name = "说明")
    private String illuStrate;
    @Excel(name = "来源(返利,消耗)")
    private String amountSource;
    @Excel(name = "时间")
    private Date time;

}