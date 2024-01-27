package io.renren.common.modules.user.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-24
 */
@Data
public class UserExcel {
    @Excel(name = "id")
    private Long memberCode;
    @Excel(name = "用户名")
    private String memberNichen;
    @Excel(name = "创建时间")
    private Date createTime;
    @Excel(name = "金币数量")
    private BigDecimal memberCion;
    @Excel(name = "提现余额")
    private BigDecimal balance;
    @Excel(name = "账号")
    private String memberName;
    @Excel(name = "用户头像")
    private String memberAvatar;
    @Excel(name = "密码")
    private String memberPwd;
    @Excel(name = "VIP到期时间")
    private Date vipDate;
    @Excel(name = "微信openid")
    private String wxOpenid;
    @Excel(name = "微信Unionid")
    private String wxUnionid;
    @Excel(name = "上級ID")
    private Long pid;

}