package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.dto.CircleCountDto;
import io.renren.common.dto.PayCountDto;
import io.renren.common.dto.PostCountDto;
import io.renren.common.dto.UserCountDto;
import io.renren.common.modules.Cami.dto.CamiDTO;
import io.renren.common.modules.Cami.excel.CamiExcel;
import io.renren.common.modules.Cami.service.CamiService;
import io.renren.common.page.PageData;
import io.renren.common.service.HomeService;
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
import java.util.*;


/**
 * 卡密表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-15
 */
@RestController
@RequestMapping("sys/home")
@Api(tags="首页")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("queryHome")
    @ApiOperation("首页数据")

    public Result queryHome(){
        //查询用户
        UserCountDto userCountDto = homeService.queryUser();
        //查询充值
        PayCountDto payCountDto = homeService.queryPay();

        //查询圈子
        CircleCountDto circleCountDto = homeService.queryCircle();
        //查询帖子
        PostCountDto postCountDto = homeService.queryPost();
        Map<String,Object> map = new HashMap<>();
        map.put("user",userCountDto);
        map.put("pay",payCountDto);
        map.put("circle",circleCountDto);
        map.put("post",postCountDto);
        return new Result().ok(map);
    }



}