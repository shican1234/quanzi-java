/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.redis;

/**
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public class SysParamsKeys {

    //默认用户头像
    public static final String USER_HEAD_URL = "USER_HEAD_URL";
    //提现比例 注册时候默认的
    public static final String TX_RATIO = "TX_RATIO";
    //注册时候默认的付费贴分成比例,就是圈主得到圈子里面别的人发的付费贴的比例
    public static final String PAY_POST_RATIO = "PAY_POST_RATIO";
    //调用IM接口时候的token  后台参数管理里面配置
    public static final String MSG_TOKEN = "MSG_TOKEN";
    //IM服务的API
    public static final String IM_API = "IM_API";
    //签到奖励经验值
    public static final String SIGN_EXP = "SIGN_EXP";
    //经验值和等级比,每个等级升级经验值=等级*这个值
    public static final String EXP_LEVEL = "EXP_LEVEL";
    //每日发布的第一个帖子增加的经验值
    public static final String POST_EXP = "POST_EXP";
    //每日首次评论加的经验值
    public static final String COMMOM_EXP = "COMMOM_EXP";
    //发送验证码方式(0短信1邮箱2全部)
    public static String CODETYPE= "CODETYPE";
    //充值一级返利比例
    public static String ONE_REBATE_RATIO= "ONE_REBATE_RATIO";
    //充值二级返利比例
    public static String TWO_REBATE_RATIO= "TWO_REBATE_RATIO";
    //vip可以创建圈子的数量
    public static String VIP_CREATE_CIRCLE_SUM= "VIP_CREATE_CIRCLE_SUM";
    //普通用户可以创建圈子的数量
    public static String USER_CREATE_CIRCLE_SUM= "USER_CREATE_CIRCLE_SUM";
    //小程序APPID
    public static String mp_appid = "mp_appid";
    //小程序秘钥
    public static String mp_secret = "mp_secret";
    //公众号APPID
    public static String gzh_appid = "gzh_appid";
    //公众号秘钥
    public static String gzh_secret = "gzh_secret";
    //打赏金额,以英文,隔开
    public static String reward_money = "reward_money";
    //微信开放平台 APP应用的APPID
    public static String wx_app_appid = "wx_app_appid";
    //微信开放平台 APP应用的秘钥
    public static String wx_app_secret = "wx_app_secret";
}
