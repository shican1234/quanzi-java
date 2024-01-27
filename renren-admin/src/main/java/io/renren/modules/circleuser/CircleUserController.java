package io.renren.modules.circleuser;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circle.service.CircleService;
import io.renren.common.modules.post.dto.PostDTO;
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
import io.renren.common.modules.circleuser.dto.CircleUserDTO;
import io.renren.common.modules.circleuser.excel.CircleUserExcel;
import io.renren.common.modules.circleuser.service.CircleUserService;
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
 * 圈子人员
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-08
 */
@RestController
@RequestMapping("circleuser/circleuser")
@Api(tags="圈子人员")
public class CircleUserController {
    @Autowired
    private CircleUserService circleUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private CircleService circleService;
    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("circleuser:circleuser:page")
    public Result<PageData<CircleUserDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("orderField","create_time");
        params.put("order","desc");
        PageData<CircleUserDTO> page = circleUserService.page(params);
        if(CollUtil.isNotEmpty(page.getList())){
            for (CircleUserDTO circleDTO : page.getList()) {
                UserEntity userEntity = userService.selectById(circleDTO.getUserId());
                if(userEntity != null){
                    circleDTO.setAvatar(userEntity.getAvatar());
                    circleDTO.setNickName(userEntity.getNickName());
                }
                //圈子名字
                CircleEntity circleEntity = circleService.selectById(circleDTO.getCircleId());
                if(circleEntity != null){
                    circleDTO.setCircleName(circleEntity.getCircleName());
                }
            }
        }
        return new Result<PageData<CircleUserDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("circleuser:circleuser:info")
    public Result<CircleUserDTO> get(@PathVariable("id") Long id){
        CircleUserDTO data = circleUserService.get(id);

        return new Result<CircleUserDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("circleuser:circleuser:save")
    public Result save(@RequestBody CircleUserDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        circleUserService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("circleuser:circleuser:update")
    public Result update(@RequestBody CircleUserDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        circleUserService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("circleuser:circleuser:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        circleUserService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("circleuser:circleuser:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CircleUserDTO> list = circleUserService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CircleUserExcel.class);
    }

}