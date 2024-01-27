package io.renren.common.modules.follow.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 关注表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-01
 */
@Data
public class FollowExcel {
    @Excel(name = "ID")
    private Long id;
    @Excel(name = "关注用户id")
    private Long fromUser;
    @Excel(name = "被关注用户id")
    private Long toUser;
    @Excel(name = "关注时间")
    private Date createTime;

}