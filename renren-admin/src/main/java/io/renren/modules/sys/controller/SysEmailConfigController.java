/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.emailconfig.EmailConfig;
import io.renren.common.entity.SysParamsEntity;
import io.renren.common.oss.service.SysOssService;
import io.renren.common.service.SysParamsService;
import io.renren.common.smsconfig.SmsConfig;
import io.renren.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("sys/emailconfig")
@Api(tags="短信配置")
public class SysEmailConfigController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysParamsService sysParamsService;

    private final static String KEY = Constant.EMAIL_CONFIG_KEY;



    @GetMapping("info")
	@ApiOperation(value = "邮箱配置信息")
    public Result<EmailConfig> info(){
		EmailConfig config = sysParamsService.getValueObject(KEY, EmailConfig.class);

        return new Result<EmailConfig>().ok(config);
    }

	@PostMapping
	@ApiOperation(value = "保存邮箱配置信息")
	@LogOperation("保存邮箱配置信息")
	public Result saveConfig(@RequestBody EmailConfig config){
		//校验类型
		String value = sysParamsService.getValue(KEY);
		if(StrUtil.isBlank(value)){
			SysParamsEntity sysParamsDTO = new SysParamsEntity();
			sysParamsDTO.setParamCode(KEY);
			sysParamsDTO.setParamValue(new Gson().toJson(config));
			sysParamsDTO.setCreateDate(new Date());
			sysParamsDTO.setUpdateDate(new Date());
			sysParamsDTO.setRemark("邮箱配置类");
			sysParamsDTO.setParamType(1);
			sysParamsService.insert(sysParamsDTO);
		}else{
			sysParamsService.updateValueByCode(KEY, new Gson().toJson(config));
		}




		return new Result();
	}


}