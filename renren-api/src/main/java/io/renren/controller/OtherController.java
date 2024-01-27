package io.renren.controller;

import cn.hutool.core.util.StrUtil;
import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.common.CommonService;
import io.renren.common.dto.SearchDTO;
import io.renren.common.dto.SendCode;
import io.renren.common.dto.UpdateDTO;
import io.renren.common.modules.appconfig.entity.AppConfigEntity;
import io.renren.common.modules.appconfig.service.AppConfigService;
import io.renren.common.modules.appupdate.entity.AppUpdateEntity;
import io.renren.common.modules.appupdate.service.AppUpdateService;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circle.service.CircleService;
import io.renren.common.modules.post.entity.PostEntity;
import io.renren.common.modules.post.service.PostService;
import io.renren.common.modules.token.entity.TokenEntity;
import io.renren.common.modules.token.service.TokenService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.redis.RedisKeys;
import io.renren.common.redis.RedisService;
import io.renren.common.redis.SysParamsKeys;
import io.renren.common.service.SysParamsService;
import io.renren.common.utils.DateUtil;
import io.renren.common.utils.IPUtil;
import io.renren.common.utils.Result;
import io.renren.common.utils.Tools;
import io.renren.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.dromara.email.api.MailClient;
import org.dromara.email.comm.config.MailSmtpConfig;
import org.dromara.email.comm.entity.MailMessage;
import org.dromara.email.core.factory.MailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/other")
@Api(tags="其他接口")
@CrossOrigin
public class OtherController {
    @Autowired
    private AppConfigService appConfigService;
    @Autowired
    private AppUpdateService appUpdateService;
    @Autowired
    private SysParamsService sysParamsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CircleService circleService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisService redisService;
    @PostMapping("getCodeType")
    @ApiOperation("获取发送验证码的方式")
    public Result getCodeType(){
        String value = sysParamsService.getValue(SysParamsKeys.CODETYPE);
        return new Result().ok(value);
    }

    @PostMapping("sendCode")
    @ApiOperation("发送验证码")
    public Result sendCode(@RequestBody SendCode reqBody) throws MessagingException {
        ValidatorUtils.validateEntity(reqBody);
        boolean email = Tools.isEmail(reqBody.getUserName());
        if(email){
            //说明是邮箱
            return commonService.sendEmail(reqBody);
        }else{
            //手机号
            return commonService.sendSms(reqBody);
        }

    }




    @PostMapping("getConfig")
    @ApiOperation("获取APP配置")
    public Result getConfig(){
        AppConfigEntity info = appConfigService.getInfo();
        return new Result().ok(info);
    }

    @PostMapping("updateInfo")
    @ApiOperation("获取APP更新")
    public Result updateInfo(@RequestBody UpdateDTO dto){
        List<AppUpdateEntity> list = appUpdateService.queryUpdateInfo(dto);
        return new Result().ok(list);
    }


    @PostMapping("getNote")
    @ApiOperation("获取弹框公告")
    public Result getNote(HttpServletRequest request){
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        Long userId = 0L;
        if(StrUtil.isNotBlank(token)){
            TokenEntity byToken = tokenService.getByToken(token);
            if(byToken != null){
                userId = byToken.getUserId();
            }
        }
        Map<String,Object> map = new HashMap<>();
        AppConfigEntity info = appConfigService.getInfo();
        //查看当前ID和IP是不是弹过今天
        String ipAddr = IPUtil.getIp(request);
        String redisKey = userId+":"+ipAddr;
        String disCode = (String) redisService.get(RedisKeys.NOTE_SHOW,redisKey);
        if(StrUtil.isNotBlank(disCode) || info.getShowPopup() == 0){
            map.put("noteShow",false);
            return new Result().ok(map);
        }
        map.put("noteShow",true);
        map.put("noteContent",info.getPopupContent());
        redisService.set(RedisKeys.NOTE_SHOW,redisKey,redisKey, DateUtil.getSecondsNextEarlyMorning());
        return new Result().ok(map);
    }



    @PostMapping("search")
    @ApiOperation("搜索")
    @Login
    public Result search(@LoginUser UserEntity user, @RequestBody SearchDTO searchDTO){
        ValidatorUtils.validateEntity(searchDTO);
        searchDTO.setPage(searchDTO.getPage() * 10);
        searchDTO.setUserId(user.getId());
        if(searchDTO.getSelectIndex() == 0){
            List<CircleEntity> list = circleService.searchCircle(searchDTO);
            return new Result().ok(list);
        }else if(searchDTO.getSelectIndex() == 1){
            List<PostEntity> list = postService.searchPost(searchDTO);
            return new Result().ok(list);
        }else{
            List<UserEntity> list = userService.searchUser(searchDTO);
            return new Result().ok(list);
        }
    }




}
