/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.json.JSONArray;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class AddVoteDTO {
   @ApiModelProperty(value = "圈子ID")
   @NotNull(message = "请选择圈子")
   private Long circleId;
   @ApiModelProperty(value = "付费价钱")
   @NotNull(message = "请选择投票时间")
   private Integer voteDay;
   @ApiModelProperty(value = "标题")
   @NotEmpty(message="标题不能为空")
   @Length(min = 2, max = 10, message = "标题长度必须在{min}和{max}之间")
   private String title;
   @ApiModelProperty(value = "内容")
   @NotEmpty(message="内容不能为空")
   @Length(min = 10, max = 300, message = "内容字数必须在{min}和{max}之间")
   private String content;

   @ApiModelProperty(value = "付费价钱")
   @NotEmpty(message = "请设置投票项")
   @Size(min= 2, max= 8,message = "投票项目必须在{min}和{max}之间")
   private List<VoteDto> lists;
   @ApiModelProperty(value = "类型(0公开帖子1圈内帖子)")
   private int type;
   @ApiModelProperty(value = "投票类型(0单选1多选)")
   private int voteType;
}
