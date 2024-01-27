package io.renren.common.common;

import cn.hutool.core.util.StrUtil;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.redis.RedisKeys;
import io.renren.common.redis.RedisService;
import io.renren.common.redis.SysParamsKeys;
import io.renren.common.service.SysParamsService;
import io.renren.common.utils.DateUtil;
import io.renren.common.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Component
@Slf4j
public class AsyncService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysParamsService sysParamsService;


    /**
     * 5分钟更新下最后活跃时间和IP
     * @param userId
     * @param request
     */
    @Async
    public void updateLoginTimeAndIp(Long userId, HttpServletRequest request) {
        String s = (String) redisService.get(RedisKeys.LOGIN_IP, userId.toString());
        if(StrUtil.isBlank(s)){
            String ipAddr = IPUtil.getIp(request);
            UserEntity user = new UserEntity();
            user.setLoginIp(ipAddr);
            user.setId(userId);
            user.setLastLoginTime(new Date());
            userService.updateById(user);
            redisService.set(RedisKeys.LOGIN_IP,userId.toString(),userId.toString(),5 * 60);
        }
    }






    /**
     *
     * @param userEntity 用户
     * @param type 类型(0签到1发帖2评论)
     */
    public void addExpAndLevel(UserEntity userEntity, int type) {
        Long exp = 0l;
        if(type == 0){
            String value = sysParamsService.getValue(SysParamsKeys.SIGN_EXP);
            exp = StrUtil.isNotBlank(value) ? Long.parseLong(value) : 5l;
        }else if(type == 1){
            String disCode = (String) redisService.get(RedisKeys.POST_EXP,userEntity.getId().toString());
            if(StrUtil.isNotBlank(disCode)){
                return ;
            }
            String value = sysParamsService.getValue(SysParamsKeys.POST_EXP);
            exp = StrUtil.isNotBlank(value) ? Long.parseLong(value) : 5l;
            redisService.set(RedisKeys.POST_EXP,userEntity.getId().toString(),userEntity.getId().toString(), DateUtil.getSecondsNextEarlyMorning());
        }else if(type == 2){
            String disCode = (String) redisService.get(RedisKeys.COMMOM_EXP,userEntity.getId().toString());
            if(StrUtil.isNotBlank(disCode)){
                return ;
            }
            String value = sysParamsService.getValue(SysParamsKeys.COMMOM_EXP);
            exp = StrUtil.isNotBlank(value) ? Long.parseLong(value) : 5l;
            redisService.set(RedisKeys.COMMOM_EXP,userEntity.getId().toString(),userEntity.getId().toString(), DateUtil.getSecondsNextEarlyMorning());
        }
        UserEntity user = new UserEntity();
        user.setId(userEntity.getId());
        Integer level = userEntity.getLevel();//当前等级
        Long experience = userEntity.getExperience();//当前经验值
        String value = sysParamsService.getValue(SysParamsKeys.EXP_LEVEL);//等级经验比
        Long exp_level = StrUtil.isNotBlank(value) ? Long.parseLong(value) : 100l;
        //升下一级需要的经验
        Long nextExp =  exp_level*(level+1);
        if(experience + exp >= nextExp){
            //等级加一 经验修改
            user.setLevel(level+1);
            user.setExperience( (experience + exp) - nextExp);
        }else{
            user.setExperience(experience + exp);
        }
        userService.updateById(user);
    }


}
