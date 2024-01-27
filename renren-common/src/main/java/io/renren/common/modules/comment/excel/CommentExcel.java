package io.renren.common.modules.comment.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 评论
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-30
 */
@Data
public class CommentExcel {
    @Excel(name = "")
    private Integer id;
    @Excel(name = "所属帖子id")
    private Long tid;
    @Excel(name = "评论人id")
    private Long commentUserId;
    @Excel(name = "所属评论id，主评论为null")
    private Long parentId;
    @Excel(name = "评论内容")
    private String content;
    @Excel(name = "点赞(存储点赞人id数组)")
    private String like;
    @Excel(name = "状态，0-未审核，1-展现，2-审核驳回，3-已删除")
    private Integer status;
    @Excel(name = "")
    private Date createTime;
    @Excel(name = "")
    private Date updateTime;

}