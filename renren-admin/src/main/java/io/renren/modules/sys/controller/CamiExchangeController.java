package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.camiExchange.dto.CamiExchangeDTO;
import io.renren.common.modules.camiExchange.excel.CamiExchangeExcel;
import io.renren.common.modules.camiExchange.service.CamiExchangeService;
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
import java.util.List;
import java.util.Map;


/**
 * 卡密兑换记录表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-16
 */
@RestController
@RequestMapping("camiExchange/camiexchange")
@Api(tags="卡密兑换记录表")
public class CamiExchangeController {
    @Autowired
    private CamiExchangeService camiExchangeService;
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
    @RequiresPermissions("camiExchange:camiexchange:page")
    public Result<PageData<CamiExchangeDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CamiExchangeDTO> page = camiExchangeService.page(params);

        List<CamiExchangeDTO> list = page.getList();
        for (CamiExchangeDTO camiExchangeDTO: list) {
            //拿到用户ID
            Long userId = camiExchangeDTO.getUserId();
            //根据用户ID查询用户数据
            UserEntity userByUserId = userService.getUserByUserId(userId);

            //拿到用户昵称
            String memberNichen = userByUserId.getNickName();
            camiExchangeDTO.setMemberNichen(memberNichen);
            camiExchangeDTO.setAvatar(userByUserId.getAvatar());
        }

        return new Result<PageData<CamiExchangeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("camiExchange:camiexchange:info")
    public Result<CamiExchangeDTO> get(@PathVariable("id") Long id){
        CamiExchangeDTO data = camiExchangeService.get(id);

        return new Result<CamiExchangeDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("camiExchange:camiexchange:save")
    public Result save(@RequestBody CamiExchangeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        camiExchangeService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("camiExchange:camiexchange:update")
    public Result update(@RequestBody CamiExchangeDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        camiExchangeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("camiExchange:camiexchange:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        camiExchangeService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("camiExchange:camiexchange:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CamiExchangeDTO> list = camiExchangeService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CamiExchangeExcel.class);
    }

}