package io.renren.common.modules.circleclass.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 圈子分类
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Data
@ApiModel(value = "圈子分类")
public class CircleClassDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "分类ID")
	private Long id;

	@ApiModelProperty(value = "分类名字")
	private String name;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 排序,越大越靠前
	 */
	private Integer sorts;
}