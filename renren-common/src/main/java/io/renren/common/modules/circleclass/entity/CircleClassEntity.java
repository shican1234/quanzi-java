package io.renren.common.modules.circleclass.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 圈子分类
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Data
@TableName("tb_circle_class")
public class CircleClassEntity {

    /**
     * 分类ID
     */
	private Long id;
    /**
     * 分类名字
     */
	private String name;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 排序,越大越靠前
     */
    private Integer sorts;
}