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
import io.renren.common.dto.SysParamsDTO;
import io.renren.common.entity.SysParamsEntity;
import io.renren.common.exception.ErrorCode;
import io.renren.common.oss.cloud.CloudStorageConfig;
import io.renren.common.oss.cloud.OSSFactory;
import io.renren.common.oss.entity.SysOssEntity;
import io.renren.common.oss.group.AliyunGroup;
import io.renren.common.oss.group.QcloudGroup;
import io.renren.common.oss.group.QiniuGroup;
import io.renren.common.oss.service.SysOssService;
import io.renren.common.page.PageData;
import io.renren.common.service.SysParamsService;
import io.renren.common.smsconfig.SmsConfig;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/smsconfig")
@Api(tags="短信配置")
public class SysSmsConfigController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysParamsService sysParamsService;

    private final static String KEY = Constant.SMS_CONFIG_KEY;



    @GetMapping("info")
	@ApiOperation(value = "短信配置信息")
    public Result<SmsConfig> info(){
		SmsConfig config = sysParamsService.getValueObject(KEY, SmsConfig.class);

        return new Result<SmsConfig>().ok(config);
    }

	@PostMapping
	@ApiOperation(value = "保存短信配置信息")
	@LogOperation("保存短信配置信息")
	public Result saveConfig(@RequestBody SmsConfig config){
		//校验类型
		String value = sysParamsService.getValue(KEY);
		if(StrUtil.isBlank(value)){
			SysParamsEntity sysParamsDTO = new SysParamsEntity();
			sysParamsDTO.setParamCode(KEY);
			sysParamsDTO.setParamValue(new Gson().toJson(config));
			sysParamsDTO.setCreateDate(new Date());
			sysParamsDTO.setUpdateDate(new Date());
			sysParamsDTO.setRemark("短信配置类");
			sysParamsDTO.setParamType(1);
			sysParamsService.insert(sysParamsDTO);
		}else{
			sysParamsService.updateValueByCode(KEY, new Gson().toJson(config));
		}




		return new Result();
	}


}