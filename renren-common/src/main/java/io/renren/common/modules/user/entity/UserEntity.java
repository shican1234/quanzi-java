package io.renren.common.modules.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.renren.common.utils.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.AUTO)
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


	@TableField(exist = false)
	private String token;
	@TableField(exist = false)
	private Boolean bindingOk;

	@TableField(exist = false)
	private Integer vipType;
	@TableField(exist = false)
	private String become;
	@TableField(exist = false)
	private Long fansCount;
	@TableField(exist = false)
	private Long postCount;
	@TableField(exist = false)
	private Date payTime;
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
	 * 提现比例
	 */
	private BigDecimal withdrawalProportion;


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
	 * 状态(0正常1封禁)
	 */
	private Integer status;
	/**
	 * 封禁理由
	 */
	private String lockReason;
	@JsonIgnore
	public boolean isVip() {
		return vipDate != null && vipDate.after(new Date());
	}
	//增加VIP时间
	public Date rechargeVip(int dayCount) {
		if (!isVip()){
			setVipDate(new Date());
		}
		if (dayCount < 0){

		}else if (dayCount == 0){ //测试商品
			setVipDate(DateUtil.getAfterMin(getVipDate(), 1));
		}else {
			setVipDate(DateUtil.getAfterDay(getVipDate(), dayCount));
		}
		return getVipDate();
	}
}