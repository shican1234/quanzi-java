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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class SubVoteDto {
   @ApiModelProperty(value = "帖子ID")
   @NotNull(message = "帖子ID不能为空")
   private Long tid;



   @NotEmpty(message = "请选择投票项")
   private List<VotesDto> lists;
}
