package io.renren.modules.circleclass;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.circleclass.entity.CircleClassEntity;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.common.modules.circleclass.dto.CircleClassDTO;
import io.renren.common.modules.circleclass.excel.CircleClassExcel;
import io.renren.common.modules.circleclass.service.CircleClassService;
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
 * 圈子分类
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@RestController
@RequestMapping("circleclass/circleclass")
@Api(tags="圈子分类")
public class CircleClassController {
    @Autowired
    private CircleClassService circleClassService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("circleclass:circleclass:page")
    public Result<PageData<CircleClassDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CircleClassDTO> page = circleClassService.page(params);

        return new Result<PageData<CircleClassDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("circleclass:circleclass:info")
    public Result<CircleClassDTO> get(@PathVariable("id") Long id){
        CircleClassDTO data = circleClassService.get(id);

        return new Result<CircleClassDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("circleclass:circleclass:save")
    public Result save(@RequestBody CircleClassDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        circleClassService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("circleclass:circleclass:update")
    public Result update(@RequestBody CircleClassDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        circleClassService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("circleclass:circleclass:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        circleClassService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("circleclass:circleclass:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CircleClassDTO> list = circleClassService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CircleClassExcel.class);
    }
    @GetMapping("circleClass")
    @ApiOperation("所有圈子分类")
    @LogOperation("所有圈子分类")
    public Result circleClass() throws Exception {

        List<CircleClassEntity> list = circleClassService.queryCircleClass();
        return new Result().ok(list);
    }
}