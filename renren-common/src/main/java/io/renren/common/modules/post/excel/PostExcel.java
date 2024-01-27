package io.renren.common.modules.post.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 帖子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Data
public class PostExcel {
    @Excel(name = "帖子ID")
    private Long id;
    @Excel(name = "帖子文字内容")
    private String content;
    @Excel(name = "文件地址,json格式")
    private String fileUrls;
    @Excel(name = "发帖的人的ID")
    private Long userId;
    @Excel(name = "圈子ID")
    private Long circleId;
    @Excel(name = "状态(0审核中1审核通过1拒绝)")
    private Integer status;
    @Excel(name = "类型(0公开帖子1圈内帖子)")
    private Integer type;
    @Excel(name = "发帖时间")
    private Date createTime;
    @Excel(name = "文件类型(0图片1视频)")
    private Integer fileType;
    @Excel(name = "话题ID")
    private Long discussId;
    @Excel(name = "标题")
    private String title;

}