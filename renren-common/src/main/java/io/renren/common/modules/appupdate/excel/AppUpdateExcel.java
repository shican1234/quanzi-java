package io.renren.common.modules.appupdate.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 更新配置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-20
 */
@Data
public class AppUpdateExcel {
    @Excel(name = "id")
    private Long id;
    @Excel(name = "版本号")
    private String version;
    @Excel(name = "更新内容")
    private String note;
    @Excel(name = "更新地址")
    private String downloadUrl;
    @Excel(name = "添加时间")
    private Date createTime;
    @Excel(name = "平台")
    private String os;

}