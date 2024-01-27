package io.renren.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "搜索")
public class SearchDTO implements Serializable {
    private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "分页数据")
	@NotNull(message="分页数据不能为空")
	private Integer page;
	@ApiModelProperty(value = "搜索内容")
	@NotEmpty(message="搜索内容不能为空")
	private String keyword;

	@ApiModelProperty(value = "类型(0搜索圈子 1搜索帖子 2搜索用户)")
	private Integer selectIndex;
	private Long userId;
}