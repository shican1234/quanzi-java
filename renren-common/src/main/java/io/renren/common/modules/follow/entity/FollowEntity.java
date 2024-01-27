package io.renren.common.modules.follow.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 关注表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-01
 */
@Data
@TableName("tb_follow")
public class FollowEntity {

    /**
     * ID
     */
	private Long id;
    /**
     * 关注用户id
     */
	private Long fromUser;
    /**
     * 被关注用户id
     */
	private Long toUser;
    /**
     * 关注时间
     */
	private Date createTime;
}