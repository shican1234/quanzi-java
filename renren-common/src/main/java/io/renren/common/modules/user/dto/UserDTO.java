package io.renren.common.modules.user.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * 用户
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-24
 */
@Data
@ApiModel(value = "用户")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
	 * 金币余额
	 */
	private BigDecimal cion;
	/**
	 * 提现余额
	 */
	private BigDecimal balance;


	/**
	 * VIP到期时间
	 */
	private Date vipDate;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 密码
	 */
	@JsonIgnore
	private String passWord;
	/**
	 * 手机号
	 */
	private String mobile;
	private String birth;
	/**
	 * 上级ID
	 */
	private Long pid;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 普通用户的标识，对当前开发者帐号唯一
	 */
	private String wxOpenid;
	/**
	 * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的
	 */
	private String wxUnionid;


	private Integer gender;

	private String newPassword;
	/**
	 * 圈子内付费贴分成比例
	 */
	private BigDecimal postProportion;
	/**
	 * 提现比例
	 */
	private BigDecimal withdrawalProportion;
	/**
	 * 一级返利比例
	 */
	private BigDecimal oneProportion;
	/**
	 * 二级返利比例
	 */
	private BigDecimal twoProportion;

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
	 * 个性签名
	 */
	private String signature;
	/**
	 * 婚姻狀態
	 */
	private String emotion;

	/**
	 * 职位
	 */
	private String job;
	/**
	 * 状态(0正常1封禁)
	 */
	private Integer status;
	/**
	 * 封禁理由
	 */
	private String lockReason;
}