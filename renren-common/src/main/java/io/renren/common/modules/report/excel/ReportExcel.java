package io.renren.common.modules.report.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 举报表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-21
 */
@Data
public class ReportExcel {
    @Excel(name = "ID")
    private Long id;
    @Excel(name = "处理进度(0待处理1已处理2已关闭)")
    private Integer status;
    @Excel(name = "举报者Id")
    private Long fromUser;
    @Excel(name = "被举报者ID")
    private Long toUser;
    @Excel(name = "举报原因(0:诱导欺骗送礼物1诱导去其他平台2聊天内容涉黄3诱导涉黄4语言暴力/骚扰)")
    private String reasons;
    @Excel(name = "举报时间")
    private Date createTime;
    @Excel(name = "举报截屏的URL,多条以^分割")
    private String screenShot;
    @Excel(name = "被举报的帖子ID")
    private Long postId;
    @Excel(name = "被举报的圈子ID")
    private Long circleId;

}