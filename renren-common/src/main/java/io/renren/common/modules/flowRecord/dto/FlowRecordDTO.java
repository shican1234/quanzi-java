package io.renren.common.modules.flowRecord.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 金币流水
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-14
 */
@Data
@ApiModel(value = "金币流水")
public class FlowRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	private Long id;

	@ApiModelProperty(value = "订单编号")
	private String code;

	@ApiModelProperty(value = "金币数量")
	private String amount;

	@ApiModelProperty(value = "所得")
	private String income;

	@ApiModelProperty(value = "收入用户ID")
	private Long awayUserId;

	@ApiModelProperty(value = "收入前金币数量")
	private String awayFrontBalance;

	@ApiModelProperty(value = "收入后金币数量")
	private String awayAfterBalance;

	@ApiModelProperty(value = "支出用户ID")
	private Long outUserId;

	@ApiModelProperty(value = "支出前金币数量")
	private String outFrontBalance;

	@ApiModelProperty(value = "支出后金币数量")
	private String outAfterBalance;

	@ApiModelProperty(value = "类型(0:打赏 1:付费圈 2:付费贴  3:发红包  4:抢红包  5:红包退回 6签到  7:付费返利  8:赞赏)")
	private Integer status;

	@ApiModelProperty(value = "通用ID(圈子ID/帖子ID或其他关联ID)")
	private Long currentId;

	@ApiModelProperty(value = "时间")
	private Date time;

	/**
	 * 收入用户昵称
	 */
	private String awayNickName;
	/**
	 * 收入用户头像
	 */
	private String awayAvatar;

	/**
	 * 支出用户昵称
	 */
	private String outNickName;
	/**
	 * 支出用户头像
	 */
	private String outAvatar;

}