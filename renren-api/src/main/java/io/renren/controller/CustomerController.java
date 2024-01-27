package io.renren.controller;

import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.modules.customer.entity.CustomerEntity;
import io.renren.common.modules.customer.service.CustomerService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 客服联系方式表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2024-01-18
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/customer")
@Api(tags="客服联系方式接口")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("queryCustomer")
    @ApiOperation("查询后台配置的客服联系方式")
    @Login
    public Result<CustomerEntity> queryCustomer(@LoginUser UserEntity user) throws Exception {

        CustomerEntity customerAll = customerService.getCustomerAll();

        return new Result<CustomerEntity>().ok(customerAll);
    }


}