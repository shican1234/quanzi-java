package io.renren.modules.appconfig;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.appconfig.entity.AppConfigEntity;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.common.modules.appconfig.dto.AppConfigDTO;
import io.renren.common.modules.appconfig.excel.AppConfigExcel;
import io.renren.common.modules.appconfig.service.AppConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * APP设置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-10
 */
@RestController
@RequestMapping("appconfig/appconfig")
@Api(tags="APP设置")
public class AppConfigController {
    @Autowired
    private AppConfigService appConfigService;


    @GetMapping("getInfo")
    @ApiOperation("信息")
    @RequiresPermissions("appconfig:appconfig:info")
    public Result<AppConfigEntity> getInfo(){
        AppConfigEntity appConfigEntity = appConfigService.getInfo();

        return new Result<AppConfigEntity>().ok(appConfigEntity);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("appconfig:appconfig:save")
    public Result save(@RequestBody AppConfigDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        appConfigService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("appconfig:appconfig:update")
    public Result update(@RequestBody AppConfigDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        appConfigService.update(dto);

        return new Result();
    }



}