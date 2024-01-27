package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.modules.withdrawal.dto.WithdrawalDTO;
import io.renren.common.modules.withdrawal.excel.WithdrawalExcel;
import io.renren.common.modules.withdrawal.service.WithdrawalService;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户提现代付表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-16
 */
@RestController
@RequestMapping("withdrawal/withdrawal")
@Api(tags="用户提现代付表")
public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;
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
    @RequiresPermissions("withdrawal:withdrawal:page")
    public Result<PageData<WithdrawalDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<WithdrawalDTO> page = withdrawalService.page(params);

        List<WithdrawalDTO> list = page.getList();
        for (WithdrawalDTO WithdrawalDTO: list) {
            //拿到用户ID
            Long userId = WithdrawalDTO.getUserId();
            //根据用户ID查询用户数据
            UserEntity userByUserId = userService.getUserByUserId(userId);

            //拿到用户昵称
            String memberNichen = userByUserId.getNickName();
            WithdrawalDTO.setMemberNichen(memberNichen);
            WithdrawalDTO.setAvatar(userByUserId.getAvatar());
        }

        return new Result<PageData<WithdrawalDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("withdrawal:withdrawal:info")
    public Result<WithdrawalDTO> get(@PathVariable("id") Long id){
        WithdrawalDTO data = withdrawalService.get(id);

        return new Result<WithdrawalDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("withdrawal:withdrawal:save")
    public Result save(@RequestBody WithdrawalDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        withdrawalService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("withdrawal:withdrawal:update")
    public Result update(@RequestBody WithdrawalDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        //拿到提现ID
        Long withdrawalId = dto.getId();
        //根据提现ID查询用户的提现数据
        WithdrawalDTO userWithdrawalDTO = withdrawalService.get(withdrawalId);
        //拿到审核状态
        Integer old_status = userWithdrawalDTO.getStatus();
        if(old_status == 2){
            return new Result().error("该提现已被拒绝,无法再次审核");
        }

        Integer new_status = dto.getStatus();

        if(new_status == 1){
            dto.setContent("审核已通过!");
        }

        if(new_status == 2){//审核拒绝 为用户加上提现金额
            //根据提现ID查询用户提现金豆数量
            BigDecimal withdrawalSpecies = dto.getWithdrawalSpecies();
            //拿到用户ID
            Long userId = dto.getUserId();
            //组装修改数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userId",userId);
            map.put("balance",withdrawalSpecies);
            //将提现金豆返还用户余额
            userService.updateUserById(map);
            //修改提现数据
            withdrawalService.update(dto);
            return new Result().ok("已拒绝,金豆已返回用户金豆余额!");
        }

        withdrawalService.update(dto);

        return new Result().ok("审核已通过!");
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("withdrawal:withdrawal:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        withdrawalService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("withdrawal:withdrawal:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<WithdrawalDTO> list = withdrawalService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, WithdrawalExcel.class);
    }

}