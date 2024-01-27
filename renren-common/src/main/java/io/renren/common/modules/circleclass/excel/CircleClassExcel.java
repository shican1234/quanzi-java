package io.renren.common.modules.circleclass.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 圈子分类
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Data
public class CircleClassExcel {
    @Excel(name = "分类ID")
    private Long id;
    @Excel(name = "分类名字")
    private String name;
    @Excel(name = "创建时间")
    private Date createTime;

}