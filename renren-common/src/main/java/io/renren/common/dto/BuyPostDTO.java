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

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class BuyPostDTO {
   @NotNull(message = "帖子ID不能为空")
   private Long tid;
}
