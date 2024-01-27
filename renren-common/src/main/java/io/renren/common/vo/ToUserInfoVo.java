package io.renren.common.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ToUserInfoVo {
    private Long id;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 昵称
     */
    private String nickName;



    /**
     * VIP到期时间
     */
    private Date vipDate;

    /**
     * 创建时间
     */
    private Date createTime;



    @TableField(exist = false)
    private Integer vipType;
    @TableField(exist = false)
    private String become;

    /**
     * 生日
     */
    private String birth;

    /**
     * 婚姻狀態
     */
    private String emotion;

    /**
     * 职位(0:行政类  1:.财务类  .2:销售类  3:技术类  4:设计类  5:市场营销类  6:其他)
     */
    private String job;

    /**
     * 城市
     */
    private String city;

    private Integer gender;



    /**
     * 等级
     */
    private Integer level;
    /**
     * 经验
     */
    private Long experience;
    /**
     * 个人中心背景图
     */
    private String bgImg;

    /**
     * 星座
     */
    private String constellation;
    /**
     * IP
     */
    private String loginIp;
    /**
     * 最后活跃时间
     */
    private Date lastLoginTime;
    /**
     * IP属地
     */
    private String ipLocate;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 关注
     */
    private Integer follow;
    /**
     * 粉丝
     */
    private Integer fans;
    /**
     * 是否关注
     */
    private Boolean isFollow;
    /**
     * 圈龄
     */
    private String circleAge;
}
