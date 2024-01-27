package io.renren.common.modules.fabulous.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 点赞记录表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Data
public class FabulousExcel {
    @Excel(name = "主键id")
    private Long id;
    @Excel(name = "动态的id")
    private Long tid;
    @Excel(name = "点赞的人的id")
    private Long userId;
    @Excel(name = "点赞的时间")
    private Date createTime;

}