/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RewardDTO {
   @NotNull(message = "帖子ID不能为空")
   private Long tid;
   @NotNull(message = "打赏金额不能为空")
   private BigDecimal money;
   private Long userId;
}
