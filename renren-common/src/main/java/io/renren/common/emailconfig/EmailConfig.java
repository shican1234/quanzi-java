/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.emailconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * 短信配置信息
 *
 *
 */
@Data
@ApiModel(value = "邮箱配置信息")
public class EmailConfig implements Serializable {
    private static final long serialVersionUID = 1L;



    private String smtpServer;
    private String emailPort;
    private String userName;
    private String passWord;


}