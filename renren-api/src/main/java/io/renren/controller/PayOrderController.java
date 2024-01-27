package io.renren.controller;

import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.modules.payOrder.entity.PayOrderEntity;
import io.renren.common.modules.payOrder.service.PayOrderService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.utils.Result;
import io.renren.common.vo.PagesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户充值记录接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/payOrder")
@Api(tags="用户充值记录接口")
public class PayOrderController {

	@Autowired
	private UserService userService;
	@Autowired
	private PayOrderService payOrderService;

    
  @PostMapping("getUserPayOrderByUserIdList")
  @ApiOperation(" 用户充值记录")
  @Login
  public Result< List<PayOrderEntity>> getUserPayOrderByUserIdList(@LoginUser UserEntity user, @RequestBody PagesVo pagesVo) throws Exception {
	  //拿到分页数据
	  Integer page = pagesVo.getPage();
	  //拿到用户ID
	  Long userId = user.getId();

	  Map<String,Object> map = new HashMap<>();
	  map.put("userId",userId);
	  map.put("page",(page - 1) * 10);

	  List<PayOrderEntity> payOrderByUserId = payOrderService.getPayOrderByUserId(map);


	  return new Result< List<PayOrderEntity>>().ok(payOrderByUserId);
  }

}


