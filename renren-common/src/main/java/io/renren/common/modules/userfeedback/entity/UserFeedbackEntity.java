package io.renren.common.modules.userfeedback.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-20
 */
@Data
@TableName("tb_user_feedback")
public class UserFeedbackEntity {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 反馈用户ID
     */
	private Long userId;
    /**
     * 联系名称
     */
    private String userName;
    /**
     * 联系方式
     */
    private String contactInformation;
    /**
     * 反馈图片
     */
    private String img;
    /**
     * 反馈内容
     */
	private String feedbackContent;
    /**
     * 回复内容
     */
    private String replyContent;
    /**
     * 回复状态(0:未回复  1:已回复)
     */
    private Integer type;
    /**
     * 是否解决(0:为解决 1:已解决)
     */
    private Integer status;
    /**
     * 反馈时间
     */
	private Date feedbackTime;
    /**
     * 回复时间
     */
    private Date replyTime;
}