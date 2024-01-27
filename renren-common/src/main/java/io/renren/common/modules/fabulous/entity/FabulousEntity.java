package io.renren.common.modules.fabulous.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 点赞记录表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Data
@TableName("tb_fabulous")
public class FabulousEntity {

    /**
     * 主键id
     */
	private Long id;
    /**
     * 动态的id
     */
	private Long tid;
    /**
     * 点赞的人的id
     */
	private Long userId;
    /**
     * 点赞的时间
     */
	private Date createTime;
    /**
     * 类型(0帖子1评论)
     */
    private Integer type;
}