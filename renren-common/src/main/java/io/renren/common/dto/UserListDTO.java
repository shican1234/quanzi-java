/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.dto;

import lombok.Data;
@Data
public class UserListDTO {
   private Integer page;
   private Integer type;
   private Long userId;
}
