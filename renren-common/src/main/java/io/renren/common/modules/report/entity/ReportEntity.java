package io.renren.common.modules.report.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 举报表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-21
 */
@Data
@TableName("tb_report")
public class ReportEntity {

    /**
     * ID
     */
	private Long id;
    /**
     * 处理进度(0待处理1已处理2已关闭)
     */
	private Integer status;
    /**
     * 举报者Id
     */
	private Long fromUser;
    /**
     * 被举报者ID
     */
	private Long toUser;
    /**
     * 举报原因(0:诱导欺骗送礼物1诱导去其他平台2聊天内容涉黄3诱导涉黄4语言暴力/骚扰)
     */
	private String reasons;
    /**
     * 举报时间
     */
	private Date createTime;
    /**
     * 举报截屏的URL,多条以^分割
     */
	private String screenShot;
    /**
     * 被举报的帖子ID
     */
	private Long postId;
    /**
     * 被举报的圈子ID
     */
	private Long circleId;
    /**
     * 举报内容
     */
    private String content;
}