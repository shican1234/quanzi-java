package io.renren.common.modules.appupdate.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 更新配置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-20
 */
@Data
@TableName("tb_app_update")
public class AppUpdateEntity {

    /**
     * id
     */
	private Long id;
    /**
     * 版本号
     */
	private String version;
    /**
     * 更新内容
     */
	private String note;
    /**
     * 更新地址
     */
	private String downloadUrl;
    /**
     * 添加时间
     */
	private Date createTime;
    /**
     * 平台
     */
	private String os;
    private Integer forces;
}