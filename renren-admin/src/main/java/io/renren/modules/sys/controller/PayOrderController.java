package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.payOrder.dto.PayOrderDTO;
import io.renren.common.modules.payOrder.excel.PayOrderExcel;
import io.renren.common.modules.payOrder.service.PayOrderService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.modules.withdrawal.dto.WithdrawalDTO;
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
 * 充值订单表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-13
 */
@RestController
@RequestMapping("payOrder/payorder")
@Api(tags="充值订单表")
public class PayOrderController {
    @Autowired
    private PayOrderService payOrderService;
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
    @RequiresPermissions("payOrder:payorder:page")
    public Result<PageData<PayOrderDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<PayOrderDTO> page = payOrderService.page(params);

        List<PayOrderDTO> list = page.getList();
        for (PayOrderDTO payOrderDTO: list) {
            //拿到用户ID
            Long userId = payOrderDTO.getUserId();
            //根据用户ID查询用户数据
            UserEntity userByUserId = userService.getUserByUserId(userId);

            //拿到用户昵称
            String memberNichen = userByUserId.getNickName();
            payOrderDTO.setMemberNichen(memberNichen);
            payOrderDTO.setAvatar(userByUserId.getAvatar());
        }
        return new Result<PageData<PayOrderDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("payOrder:payorder:info")
    public Result<PayOrderDTO> get(@PathVariable("id") Long id){
        PayOrderDTO data = payOrderService.get(id);

        return new Result<PayOrderDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("payOrder:payorder:save")
    public Result save(@RequestBody PayOrderDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setFoundTime(new Date());
        payOrderService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("payOrder:payorder:update")
    public Result update(@RequestBody PayOrderDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        payOrderService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("payOrder:payorder:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        payOrderService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("payOrder:payorder:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PayOrderDTO> list = payOrderService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, PayOrderExcel.class);
    }

}