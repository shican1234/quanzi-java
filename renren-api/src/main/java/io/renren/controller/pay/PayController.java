package io.renren.controller.pay;

import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.modules.commodity.entity.CommodityEntity;
import io.renren.common.modules.commodity.service.CommodityService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.utils.Result;
import io.renren.vo.PayConFigVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 支付类
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pay")
@Api(tags="充值支付类")
public class PayController {

	@Autowired
	private UserService userService;
	@Autowired
	private CommodityService commodityService;

    
  @PostMapping("getGoodsLists")
  @ApiOperation("查询金币充值商品")
  @Login
  public Result<Map<String,List>> getGoodsLists(@LoginUser UserEntity user,@RequestBody PayConFigVo payConFigVo) throws Exception {

	  //查询金币充值商品数据
	  List<CommodityEntity> optionList = commodityService.getCommodityByType(1);

	  Map<String,List> mapList = new HashMap<>();

	  //拿到端別 端别类型 1:APP  2:H5  3:小程序
	  Integer type = payConFigVo.getType();

	  List<Integer> list = new ArrayList<>();

	  Map<String,Object> map = new HashMap<>();

	  mapList.put("optionList",optionList);
	  mapList.put("payList",null);

	  return new Result<Map<String,List>>().ok(mapList);
  }

	@PostMapping("getGoodsVipLists")
	@ApiOperation("查询VIP充值商品")
	@Login
	public Result<Map<String,List>> getGoodsVipLists(@LoginUser UserEntity user ,@RequestBody PayConFigVo payConFigVo) throws Exception {
		//查询金币充值商品数据
		List<CommodityEntity> optionList = commodityService.getCommodityByType(2);

		Map<String,List> mapList = new HashMap<>();
		
		//拿到端別 端别类型 1:APP  2:H5  3:小程序
		Integer type = payConFigVo.getType();

		List<Integer> list = new ArrayList<>();

		Map<String,Object> map = new HashMap<>();



		mapList.put("optionList",optionList);
		mapList.put("payList",null);

		return new Result<Map<String,List>>().ok(mapList);
	}


	@PostMapping("gopay")
	@ApiOperation("统一充值")
	@Login
	public Result<Map<String, Object>> gopay(@LoginUser UserEntity user) throws Exception {

			return new Result<Map<String, Object>>().error("充值通道暂未开启!!");


    }
}


