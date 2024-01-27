package io.renren.common.modules.circleuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 圈子人员
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-08
 */
@Data
@TableName("tb_circle_user")
public class CircleUserEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 圈子ID
     */
	private Long circleId;
    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 入圈时间
     */
	private Date createTime;
    /**
     * 类型(0普通用户1特邀嘉宾2管理员3圈主)
     */
	private Integer type;
}