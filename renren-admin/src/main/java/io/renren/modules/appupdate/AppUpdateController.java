package io.renren.modules.appupdate;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.common.modules.appupdate.dto.AppUpdateDTO;
import io.renren.common.modules.appupdate.excel.AppUpdateExcel;
import io.renren.common.modules.appupdate.service.AppUpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 更新配置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-20
 */
@RestController
@RequestMapping("appupdate/appupdate")
@Api(tags="更新配置")
public class AppUpdateController {
    @Autowired
    private AppUpdateService appUpdateService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("appupdate:appupdate:page")
    public Result<PageData<AppUpdateDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<AppUpdateDTO> page = appUpdateService.page(params);

        return new Result<PageData<AppUpdateDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("appupdate:appupdate:info")
    public Result<AppUpdateDTO> get(@PathVariable("id") Long id){
        AppUpdateDTO data = appUpdateService.get(id);

        return new Result<AppUpdateDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("appupdate:appupdate:save")
    public Result save(@RequestBody AppUpdateDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setCreateTime(new Date());
        appUpdateService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("appupdate:appupdate:update")
    public Result update(@RequestBody AppUpdateDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        appUpdateService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("appupdate:appupdate:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        appUpdateService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("appupdate:appupdate:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<AppUpdateDTO> list = appUpdateService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, AppUpdateExcel.class);
    }

}