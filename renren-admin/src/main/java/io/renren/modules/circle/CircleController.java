package io.renren.modules.circle;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circleclass.entity.CircleClassEntity;
import io.renren.common.modules.circleclass.service.CircleClassService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.common.modules.circle.dto.CircleDTO;
import io.renren.common.modules.circle.excel.CircleExcel;
import io.renren.common.modules.circle.service.CircleService;
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
 * 圈子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@RestController
@RequestMapping("circle/circle")
@Api(tags="圈子表")
public class CircleController {
    @Autowired
    private CircleService circleService;
    @Autowired
    private CircleClassService circleClassService;
    @Autowired
    private UserService userService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("circle:circle:page")
    public Result<PageData<CircleDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("orderField","time");
        params.put("order","desc");
        PageData<CircleDTO> page = circleService.page(params);
        if(CollUtil.isNotEmpty(page.getList())){
            for (CircleDTO circleDTO : page.getList()) {
                CircleClassEntity circleClassEntity = circleClassService.selectById(circleDTO.getCircleClassId());
                UserEntity userEntity = userService.selectById(circleDTO.getUserId());
                circleDTO.setAvatar(userEntity.getAvatar());
                circleDTO.setNickName(userEntity.getNickName());
                circleDTO.setCircleClassName(circleClassEntity.getName());
            }
        }


        return new Result<PageData<CircleDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("circle:circle:info")
    public Result<CircleDTO> get(@PathVariable("id") Long id){
        CircleDTO data = circleService.get(id);

        return new Result<CircleDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("circle:circle:save")
    public Result save(@RequestBody CircleDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        circleService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("circle:circle:update")
    public Result update(@RequestBody CircleDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        circleService.update(dto);

        return new Result();
    }
    @PostMapping("/hot")
    @ApiOperation("圈子热门修改")
    @LogOperation("圈子热门修改")
    public Result hot(@RequestBody CircleDTO dto){
        CircleEntity circleEntity = new CircleEntity();
        circleEntity.setHotOk(dto.getHotOk());
        circleEntity.setId(dto.getId());
        circleService.updateById(circleEntity);
        CircleEntity circleEntity1 = circleService.selectById(dto.getId());
        return new Result().ok(circleEntity1);
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("circle:circle:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        circleService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("circle:circle:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CircleDTO> list = circleService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CircleExcel.class);
    }

}