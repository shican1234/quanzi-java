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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class AddHongBaoDTO {
   @NotNull(message = "请选择圈子")
   private Long circleId;
   @ApiModelProperty(value = "红包类型(luck就是拼手气normal瓜分)")
   private String typeClass;
   @ApiModelProperty(value = "红包个数")
   @NotNull(message = "红包个数不能小于1")
   private Integer number;
   @ApiModelProperty(value = "红包总金额")
   @NotNull(message="金额不能为空")
   private BigDecimal allMoney;
   @ApiModelProperty(value = "祝福语")
   @NotEmpty(message="标题不能为空")
   @Length(min = 1, max = 8, message = "祝福语必须在{min}和{max}之间")
   private String blessing;
   @ApiModelProperty(value = "瓜分类型红包时候,单个红包金额")
   private BigDecimal oneMoney;
}
