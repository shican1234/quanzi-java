package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.flowRecord.dto.FlowRecordDTO;
import io.renren.common.modules.flowRecord.excel.FlowRecordExcel;
import io.renren.common.modules.flowRecord.service.FlowRecordService;
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
 * 金币流水
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-14
 */
@RestController
@RequestMapping("flowRecord/flowrecord")
@Api(tags="金币流水")
public class FlowRecordController {
    @Autowired
    private FlowRecordService flowRecordService;
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
    @RequiresPermissions("flowRecord:flowrecord:page")
    public Result<PageData<FlowRecordDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<FlowRecordDTO> page = flowRecordService.page(params);

        List<FlowRecordDTO> list = page.getList();
        for (FlowRecordDTO flowRecordDTO: list) {
            //拿到收入用户ID
            Long awayUserId = flowRecordDTO.getAwayUserId();
            //拿到支出用户ID
            Long outUserId = flowRecordDTO.getOutUserId();

            //根据收入用户ID查询用户数据
            UserEntity awayUserByUserId = userService.getUserByUserId(awayUserId);

            //根据收入用户ID查询用户数据
            UserEntity outUserByUserId = userService.getUserByUserId(outUserId);

            flowRecordDTO.setAwayNickName(awayUserByUserId.getNickName());//收入用户昵称
            flowRecordDTO.setAwayAvatar(awayUserByUserId.getAvatar());//收入用户头像
            if(outUserByUserId != null){
                flowRecordDTO.setOutNickName(outUserByUserId.getNickName());//支出用户昵称
                flowRecordDTO.setOutAvatar(outUserByUserId.getAvatar());//支出用户头像
            }
        }


        return new Result<PageData<FlowRecordDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("flowRecord:flowrecord:info")
    public Result<FlowRecordDTO> get(@PathVariable("id") Long id){
        FlowRecordDTO data = flowRecordService.get(id);

        return new Result<FlowRecordDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("flowRecord:flowrecord:save")
    public Result save(@RequestBody FlowRecordDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setTime(new Date());

        flowRecordService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("flowRecord:flowrecord:update")
    public Result update(@RequestBody FlowRecordDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        flowRecordService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("flowRecord:flowrecord:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        flowRecordService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("flowRecord:flowrecord:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<FlowRecordDTO> list = flowRecordService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, FlowRecordExcel.class);
    }

}