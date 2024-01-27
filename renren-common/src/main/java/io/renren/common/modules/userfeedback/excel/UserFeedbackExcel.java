package io.renren.common.modules.userfeedback.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-20
 */
@Data
public class UserFeedbackExcel {
    @Excel(name = "主键ID")
    private Long id;
    @Excel(name = "反馈用户ID")
    private Long userId;
    @Excel(name = "反馈内容")
    private String content;
    @Excel(name = "反馈时间")
    private Date time;

}