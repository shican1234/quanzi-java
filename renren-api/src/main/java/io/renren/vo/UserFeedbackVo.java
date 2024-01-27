/**
 /**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "用户反馈表单")
public class UserFeedbackVo {


    @ApiModelProperty(value = "反馈内容")
    @NotBlank(message="请输入反馈内容")
    private String feedbackContent;

    @ApiModelProperty(value = "联系人名称")
    private String userName;

    @ApiModelProperty(value = "联系方式")
    @NotBlank(message="联系方式")
    private String contactInformation;

    @ApiModelProperty(value = "反馈图片")
    private List<String> fileUrls;

    @ApiModelProperty(value = "ID")
    private Long id;

}