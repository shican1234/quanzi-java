package io.renren.controller;

import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.modules.withdrawal.entity.WithdrawalEntity;
import io.renren.common.modules.withdrawal.service.WithdrawalService;
import io.renren.common.service.SysParamsService;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.vo.PagesVo;
import io.renren.common.vo.WithdrawalVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户提现接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/member")
@Api(tags="用户提现接口")
public class WithdrawalController {

	@Autowired
	private UserService userService;
	@Autowired
	private SysParamsService paramsService;
	@Autowired
	private WithdrawalService withdrawalService;

    
  @PostMapping("userWithdrawal")
  @ApiOperation("用户提现")
  @Login
  public Result<WithdrawalEntity> userWithdrawal(@LoginUser UserEntity user, @RequestBody WithdrawalVo withdrawalVo) throws Exception {

	  //表单校验
	  ValidatorUtils.validateEntity(withdrawalVo);

	  //拿到用户提现余额
	  BigDecimal balance = user.getBalance();
	  //拿到用户ID
	  Long memberCode = user.getId();

	  //获取单次最低提现金额(金币数量)
	  String LOW_WITHDRAWAL = paramsService.getValue("LOW_WITHDRAWAL");

	  //拿到提现金额数量
	  BigDecimal money = withdrawalVo.getMoney();
	  if(money.compareTo(new BigDecimal(LOW_WITHDRAWAL)) == -1){
		  return new Result<WithdrawalEntity>().error("本次最低提现金额为:"+LOW_WITHDRAWAL+"金币!");
	  }

	  if(balance.compareTo(money) == -1){
		  return new Result<WithdrawalEntity>().error("提现余额不足!");
	  }

	  //将提现金币数量截取为两位小数
	  money = money.setScale(2,BigDecimal.ROUND_DOWN);

	  //拿到RMB兑换金币的比例
	  String WITHDRAWAL_RATIO = paramsService.getValue("WITHDRAWAL_RATIO");

	  //计算提现金币数量等于多少RMB
	  BigDecimal divide = money.divide(new BigDecimal(WITHDRAWAL_RATIO)).setScale(2,BigDecimal.ROUND_DOWN);

	  //拿到用户的提现比例
	  BigDecimal withdrawalProportion = user.getWithdrawalProportion().divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);

	  //计算用户提现实际到账(元)
	  BigDecimal bigDecimal = divide.multiply(withdrawalProportion).setScale(2, BigDecimal.ROUND_DOWN);

	  //计算用户扣除的手续费
	  BigDecimal subtract1 = divide.subtract(bigDecimal);

	  //提现成功后扣除用户提现余额数量  用户剩余提现余额
	  BigDecimal subtract = balance.subtract(money);

	  //添加一条提现数据
	  WithdrawalEntity withdrawalEntity = new WithdrawalEntity();
	  withdrawalEntity.setWithdrawalCode("测试订单号");//提现单号
	  withdrawalEntity.setUserId(memberCode);//用户ID
	  withdrawalEntity.setBankName(withdrawalVo.getBankName());//银行名称
	  withdrawalEntity.setBankNo(withdrawalVo.getBankNumber());//银行卡号
	  withdrawalEntity.setName(withdrawalVo.getName());//真实姓名
	  withdrawalEntity.setIdno(withdrawalVo.getIdno());//身份证号
	  withdrawalEntity.setWithdrawalSpecies(money);//提现金币数量
	  withdrawalEntity.setWithdrawalMoney(divide);//提现金额(金币换算的人民币元)
	  withdrawalEntity.setActualAccount(bigDecimal);//提现实际到账金额(扣除手续费)
	  withdrawalEntity.setServiceCharge(subtract1);//提现手续费
	  withdrawalEntity.setServiceChargeScale(withdrawalProportion);//提现手续费比例(%)
	  withdrawalEntity.setWithdrawalSpecies(money);//提现金币数量
	  withdrawalEntity.setFrontSpecies(balance);//提现前的提现余额
	  withdrawalEntity.setBehindSpecies(subtract);//提现后的提现余额
	  withdrawalEntity.setStatus(0);//审核状态(0:待审核  1:审核通过  2:审核拒绝)
	  withdrawalEntity.setCreateTime(new Date());//创建时间

	  withdrawalService.insert(withdrawalEntity);

	  //修改用户余额
	  Map<String,Object> map = new HashMap<String,Object>();
	  map.put("txbalance",subtract);//用户余额
	  map.put("userId",memberCode);//VIP到期时间
	  userService.updateUserById(map);

	  return new Result<WithdrawalEntity>().ok(null);
  }


	@PostMapping("getWithdrawalByUserId")
	@ApiOperation(" 用户提现记录")
	@Login
	public Result<List<WithdrawalEntity>> getWithdrawalByUserId(@LoginUser UserEntity user, @RequestBody PagesVo pagesVo) throws Exception {

	  //拿到分页数据
		Integer page = pagesVo.getPage();

		//拿到用户ID
		Long userId = user.getId();

		Map<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		map.put("page",(page - 1) * 10);

		List<WithdrawalEntity> withdrawalByUserId = withdrawalService.getWithdrawalByUserId(map);

		return new Result< List<WithdrawalEntity>>().ok(withdrawalByUserId);
	}

}


