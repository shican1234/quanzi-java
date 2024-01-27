package io.renren.common.modules.circleuser.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 圈子人员
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-08
 */
@Data
public class CircleUserExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "圈子ID")
    private Long circleId;
    @Excel(name = "用户ID")
    private Long userId;
    @Excel(name = "入圈时间")
    private Date createTime;
    @Excel(name = "类型(0普通用户1特邀嘉宾2管理员3圈主)")
    private Integer type;

}