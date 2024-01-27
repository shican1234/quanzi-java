/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.smsconfig;

import io.renren.common.oss.group.AliyunGroup;
import io.renren.common.oss.group.QcloudGroup;
import io.renren.common.oss.group.QiniuGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 短信配置信息
 *
 *
 */
@Data
@ApiModel(value = "短信配置信息")
public class SmsConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型 1：阿里云短信  2：腾讯云短信")
    @Range(min=1, max=6)
    private Integer type;

    private String aliyunAccessKeyId;
    private String aliyunAccessKeySecret;
    private String aliyunSignature;
    private String aliyunTemplateId;
    private String aliyunTemplateName;
    private String tencentAccessKeyId;
    private String tencentAccessKeySecret;
    private String tencentSignature;
    private String tencentTemplateId;
    private String tencentAppId;
    private String tencentAppKey;

}