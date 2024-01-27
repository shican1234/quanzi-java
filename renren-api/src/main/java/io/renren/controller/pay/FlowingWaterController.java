package io.renren.controller.pay;

import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.modules.flowRecord.entity.FlowRecordEntity;
import io.renren.common.modules.flowRecord.service.FlowRecordService;
import io.renren.common.modules.payOrder.service.PayOrderService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.withdrawal.service.WithdrawalService;
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
 * 用户流水
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/member")
@Api(tags="用户流水")
public class FlowingWaterController {

	@Autowired
	private PayOrderService payOrderService;
	@Autowired
	private FlowRecordService flowRecordService;
	@Autowired
	private WithdrawalService withdrawalService;

    
  @PostMapping("getFlowRecordByUserId")
  @ApiOperation("查询用户流水记录")
  @Login
  public Result<List<FlowRecordEntity>> getFlowRecordByUserId(@LoginUser UserEntity user, @RequestBody PagesVo pagesVo) throws Exception {
	  //拿到分页数据
	  Integer page = pagesVo.getPage();
	  //拿到用户ID
	  Long userId = user.getId();


	  Map<String,Object> map = new HashMap<>();
	  map.put("userId",userId);
	  map.put("page",(page - 1) * 10);

	  //根据用户ID查询用户的金币流水数据
	  List<FlowRecordEntity> flowRecordByUserId = flowRecordService.getFlowRecordByUserId(map);

	  for(FlowRecordEntity flowRecordEntity : flowRecordByUserId){
		  //拿到支出用户ID
		  Long outUserId = flowRecordEntity.getOutUserId();

		  //拿到收入用户ID
		  Long awayUserId = flowRecordEntity.getAwayUserId();

		  //拿到扣除数量
		  String amount = flowRecordEntity.getAmount();

		  //拿到收入金币数量
		  String income = flowRecordEntity.getIncome();

		  if (outUserId != null && outUserId.equals(userId)){
			  flowRecordEntity.setNumber("-"+amount);
		  }

		  if (awayUserId != null && awayUserId.equals(userId)){
			  flowRecordEntity.setNumber("+"+income);
		  }

	  }

	  return new Result<List<FlowRecordEntity>>().ok(flowRecordByUserId);
  }

}


