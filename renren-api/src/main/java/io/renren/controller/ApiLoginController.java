/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.renren.annotation.Login;
import io.renren.common.common.CommonService;
import io.renren.common.dto.BindMobileLoginDTO;
import io.renren.common.dto.WeChatLoginDTO;
import io.renren.common.modules.token.service.TokenService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.dto.LoginDTO;
import io.renren.common.redis.SysParamsKeys;
import io.renren.common.service.SysParamsService;
import io.renren.common.utils.DateUtil;
import io.renren.common.utils.HttpUtil;
import io.renren.common.utils.RandomUtil;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.renren.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 登录接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
@CrossOrigin
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private CommonService commonService;
    @Autowired
    private SysParamsService sysParamsService;

    @PostMapping("login")
    @ApiOperation("登录")
    public Result login(@RequestBody LoginDTO dto){
        //表单校验
        ValidatorUtils.validateEntity(dto);
        UserEntity user = null;
        if(dto.getLoginType() == 1){
            user = userService.login(dto);
        }else{
            boolean b = commonService.verifySmsCode(dto.getUserName(), dto.getCode());
            if(!b){
                return new Result().error("验证码有误!");
            }
            user = commonService.registerAndLogin(dto);
        }
        return new Result().ok(user);
    }
    @PostMapping("mplogin")
    @ApiOperation("小程序登入")
    public Result mplogin(@RequestBody WeChatLoginDTO dto) throws WxErrorException {
        //表单校验
        ValidatorUtils.validateEntity(dto);
        WxMaDefaultConfigImpl configStorage = new WxMaDefaultConfigImpl();
        configStorage.setMsgDataFormat("JSON");
        configStorage.setAppid(sysParamsService.getValue(SysParamsKeys.mp_appid));
        configStorage.setSecret(sysParamsService.getValue(SysParamsKeys.mp_secret));
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(configStorage);
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(dto.getCode());
        String openid = session.getOpenid();
        String unionId = openid;//就当小程序公众号等没有在微信开放平台绑定在一起的情况
        //如果开启了UnionId
        if (StrUtil.isNotBlank(session.getUnionid())) {//取到了 说明绑定了
            unionId = session.getUnionid();
        }
        String avatar = sysParamsService.getValue(SysParamsKeys.USER_HEAD_URL);
        String nickname = RandomUtil.getRandomNickname(0);
        return commonService.mpWeChatLogin(dto,unionId,nickname,avatar,openid);
    }


    @Login
    @PostMapping("logout")
    @ApiOperation("退出")
    public Result logout(@ApiIgnore @RequestAttribute("userId") Long userId){
        tokenService.expireToken(userId);
        return new Result();
    }
    @GetMapping("getWxGZHAppid")
    @ApiOperation("微信公众号登录获取公众号appid")
    public Result<String> getWxGZHAppid(){
        //表单校验
        String mpWxLoginAppid = sysParamsService.getValue(SysParamsKeys.gzh_appid);
        if(StrUtil.isBlank(mpWxLoginAppid)){
            return new Result<String>().error("暂未配置公众号信息");
        }
        return new Result<String>().ok(mpWxLoginAppid);
    }
    @PostMapping("mpWeChatLogin")
    @ApiOperation("微信公众号授权登陆")
    public Result mpWeChatLogin(@RequestBody WeChatLoginDTO dto) throws WxErrorException {
        //表单校验
        ValidatorUtils.validateEntity(dto);
        WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
        configStorage.setAppId(sysParamsService.getValue(SysParamsKeys.gzh_appid));
        configStorage.setSecret(sysParamsService.getValue(SysParamsKeys.gzh_secret));
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        WxOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.getOAuth2Service().getAccessToken(dto.getCode());
        WxOAuth2UserInfo wxMpUser = wxMpService.getOAuth2Service().getUserInfo(wxMpOAuth2AccessToken, null);
        String openid = wxMpUser.getOpenid();
        String unionId = openid;//就当小程序公众号等没有在微信开放平台绑定在一起的情况
        //如果开启了UnionId
        if (StrUtil.isNotBlank(wxMpUser.getUnionId())) {//取到了 说明绑定了
            unionId = wxMpUser.getUnionId();
        }
        String nickname = wxMpUser.getNickname();
        String headImgUrl = wxMpUser.getHeadImgUrl();
        return commonService.mpWeChatLogin(dto,unionId,nickname,headImgUrl,openid);
    }

    @PostMapping("wxApplogin")
    @ApiOperation("微信APP登入")
    public Result wxApplogin(@RequestBody WeChatLoginDTO dto) throws WxErrorException, IOException {
        //表单校验
        ValidatorUtils.validateEntity(dto);
        String code = dto.getCode();
        String appid = sysParamsService.getValue(SysParamsKeys.wx_app_appid);
        String secret = sysParamsService.getValue(SysParamsKeys.wx_app_secret);
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", appid, secret,code);

        JSONObject jsonObject = HttpUtil.doGetJson(url);
        String openid = jsonObject.getString("openid");
        String unionId = openid;//就当小程序公众号等没有在微信开放平台绑定在一起的情况
        //如果开启了UnionId
        if (StrUtil.isNotBlank(jsonObject.getString("unionid"))) {//取到了 说明绑定了
            unionId = jsonObject.getString("unionid");
        }
        String token = jsonObject.getString("access_token");
        String infoUrl = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", token, openid);
        JSONObject userInfo = HttpUtil.doGetJson(infoUrl);


        String avatar = sysParamsService.getValue(SysParamsKeys.USER_HEAD_URL);
        String nickname = RandomUtil.getRandomNickname(0);
        if (userInfo != null && openid != null) {
            nickname = userInfo.getString("nickname");
            String headimgurl = userInfo.getString("headimgurl");
            avatar = headimgurl.replace("\\", "");
        }
        return commonService.mpWeChatLogin(dto,unionId,nickname,avatar,openid);
    }
}