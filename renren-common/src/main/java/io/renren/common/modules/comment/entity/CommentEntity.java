package io.renren.common.modules.comment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 评论
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-30
 */
@Data
@TableName("tb_comment")
public class CommentEntity {

    /**
     * 
     */
	private Integer id;
    /**
     * 所属帖子id
     */
	private Long tid;
    /**
     * 评论人id
     */
	private Long commentUserId;
    /**
     * 所属评论id，主评论为null
     */
	private Long parentId;
    /**
     * 评论内容
     */
	private String content;
    /**
     * 状态，0-未审核，1-展现，2-审核驳回
     */
	private Integer status;
    /**
     * 
     */
	private Date createTime;
    /**
     * 
     */
	private Date updateTime;

    /**
     * 点赞数量
     */
    private Integer likeNum;
}