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
public class RedisKeys {
    /**
     * 系统参数Key
     */
    public static String getSysParamsKey(){
        return "sys:params";
    }

    /**
     * 验证码Key
     */
    public static String getCaptchaKey(String uuid){
        return "sys:captcha:" + uuid;
    }

    /**
     * 登录用户Key
     */
    public static String getSecurityUserKey(Long id){
        return "sys:security:user:" + id;
    }

    /**
     * 系统日志Key
     */
    public static String getSysLogKey(){
        return "sys:log";
    }

    /**
     * 系统资源Key
     */
    public static String getSysResourceKey(){
        return  "sys:resource";
    }

    /**
     * 用户菜单导航Key
     */
    public static String getUserMenuNavKey(Long userId){
        return "sys:user:nav:" + userId;
    }

    /**
     * 用户权限标识Key
     */
    public static String getUserPermissionsKey(Long userId){
        return "sys:user:permissions:" + userId;
    }

    /**
     * 重复提交锁
     */
    public final static String SUBMIT_FORM = "submit_form:";
    /**
     *  key: mobile
     *  value: smsCode
     *  过期时间5分钟
     */
    public final static String SMS_CODE = "sms_code:";
    /**
     *  key: userId
     *  value: userId
     *  过期时间5分钟
     */
    public final static String LOGIN_IP = "login_ip:";
    public final static String POST_EXP = "POST_EXP:";
    public final static String COMMOM_EXP = "COMMOM_EXP:";
    /**
     *  key: online_anchor_schedule_task_server_number
     *  value: 服务器编号 serverNo 运行jar包时动态传入 java -jar xxx.jar --serverNo=1
     *  过期时间70S
     */
    public final static String ONLINE_ANCHOR_SCHEDULE_TASK_SERVER_NUMBER = "online_anchor_schedule_task_server_number";
    /**
     *  公共弹框是否弹过
     *  key: userId+ip
     *  value: userId+ip
     *  过期时间到第二天0点
     */
    public final static String NOTE_SHOW = "NOTE_SHOW:";

}
