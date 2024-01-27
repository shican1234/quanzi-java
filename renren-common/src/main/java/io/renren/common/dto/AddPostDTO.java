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
public class AddPostDTO {
   @NotNull(message = "请选择圈子")
   private Long circleId;
   @ApiModelProperty(value = "付费类型(0不付费1文字付费2图片/视频付费3全部付费)")
   private int payType;
   @ApiModelProperty(value = "类型(0公开帖子1圈内帖子)")
   private int type;
   @ApiModelProperty(value = "文件url")
   @NotEmpty(message="请最少上传图片或者视频")
   private List<String> fileUrls;
   @ApiModelProperty(value = "付费价钱")
   private BigDecimal payPrice;
   @ApiModelProperty(value = "标题")
   @NotEmpty(message="标题不能为空")
   @Length(min = 2, max = 10, message = "标题长度必须在{min}和{max}之间")
   private String title;
   @ApiModelProperty(value = "内容")
   @NotEmpty(message="内容不能为空")
   @Length(min = 10, max = 300, message = "内容长度必须在{min}和{max}之间")
   private String content;
   private Long discussId;
}
