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

@Data
public class PayRecordUserListDTO {
   @NotNull(message = "页数不能为空")
   private Integer page;
   private Long tid;
   private Long userId;
   private Integer status;
}
