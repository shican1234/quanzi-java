/**
 /**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 登录表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@ApiModel(value = "用户反馈返回表单")
public class FeedbackReturnVo {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "反馈时间")
    private String createTime;

    @ApiModelProperty(value = "回复时间")
    private String updateTime;

    @ApiModelProperty(value = "反馈标题")
    private String title;

    @ApiModelProperty(value = "反馈内容")
    private String content;

    @ApiModelProperty(value = "1:已回复  2:待回复")
    private String state;

    @ApiModelProperty(value = "已回复/待回复")
    private String stateName;

    @ApiModelProperty(value = "回复内容")
    private String reply;

    @ApiModelProperty(value = "反馈图片集合")
    private List<Map<String,Object>> images;

    @ApiModelProperty(value = "是否解决(0:未解决 1:已解决  2:未知)")
    private String solve;


}