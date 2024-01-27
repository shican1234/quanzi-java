package io.renren.common.modules.circle.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.dto.AddCircleDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 圈子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Data
@TableName("tb_circle")
public class CircleEntity {

    /**
     * 主键ID
     */
	private Long id;
    /**
     * 圈主ID
     */
	private Long userId;
    /**
     * 圈子分类(分类ID)
     */
	private Long circleClassId;
    /**
     * 圈子介绍
     */
	private String circleIntroduce;
    /**
     * 圈子头像
     */
	private String circleSculpture;
    /**
     * 圈子背景
     */
	private String circleBackground;
    /**
     * 审核状态(0:待审核  1:通过  2:拒绝) 没用了
     */
	private Integer status;

    /**
     * 理由(审核通过无需理由,审核拒绝需填写拒绝理由)
     */
	private String reason;
    /**
     * 圈子创建时间
     */
	private Date time;
    /**
     * 是否热门圈子(0不是1是)
     */
	private Integer hotOk;
    @ApiModelProperty(value = "圈子名称")
    private String circleName;
    //帖子数
    @TableField(exist = false)
    private int postCount;
    //加入人数
    @TableField(exist = false)
    private int peopleCount;
    @TableField(exist = false)
    private String nickName;
    //加入人数
    @TableField(exist = false)
    private String avatar;
    public CircleEntity(){};
    public CircleEntity(AddCircleDTO reqBody) {
        this.circleClassId  = reqBody.getCircleClassId();
        this.circleIntroduce  = reqBody.getCircleIntroduce();
        this.circleSculpture  = reqBody.getCircleSculpture();
        this.circleBackground  = reqBody.getCircleBackground();

        this.circleName  = reqBody.getCircleName();
        this.status  = 1;
        this.time  = new Date();
        this.hotOk  = 0;
    }
}