package io.renren.controller;

import cn.hutool.core.util.StrUtil;
import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.common.CommonService;
import io.renren.common.constant.Constant;
import io.renren.common.convert.ReadConfig;
import io.renren.common.dto.SendCode;
import io.renren.common.emailconfig.EmailConfig;
import io.renren.common.modules.payOrder.entity.PayOrderEntity;
import io.renren.common.modules.payOrder.service.PayOrderService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.redis.RedisKeys;
import io.renren.common.redis.RedisService;
import io.renren.common.service.SysParamsService;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.vo.AccountBinDingVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dromara.email.api.MailClient;
import org.dromara.email.comm.config.MailSmtpConfig;
import org.dromara.email.comm.entity.MailMessage;
import org.dromara.email.core.factory.MailFactory;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.dromara.sms4j.comm.utils.SmsUtils;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户账号绑定相关接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/accountBinDing")
@Api(tags="用户账号绑定相关接口")
public class AccountBinDingController {

	@Autowired
	private UserService userService;
	@Autowired
	private SysParamsService paramsService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private SysParamsService sysParamsService;
	@Autowired
	private ReadConfig config;
	@Autowired
	private CommonService commonService;

    
  @PostMapping("getUserBuUserId")
  @ApiOperation(" 根基账号类型获取用户账号信息及后台配置")
  @Login
  public Result<Map<String,Object>> getUserPayOrderByUserIdList(@LoginUser UserEntity user){
	  
	  //拿到用户的邮箱账号
	  String email = user.getEmail();

	  //拿到用户的手机账号
	  String mobile = user.getMobile();

	  //拿到后台配置的验证码发送类型 (0:手机短信 1:邮箱验证 2:全部)
	  String CODETYPE = paramsService.getValue("CODETYPE");

	  //向前端返回数据
	  Map<String,Object> map = new HashMap<>();

	  map.put("email",email);
	  map.put("mobile",mobile);
	  map.put("code_type",CODETYPE);

	  return new Result<Map<String,Object>>().ok(map);
  }


	@PostMapping("binDingSendCode")
	@ApiOperation("绑定发送验证码")
	@Login
	public Result binDingSendCode(@LoginUser UserEntity user ,@RequestBody AccountBinDingVo accountBinDingVo) throws MessagingException {
		//表单校验
		ValidatorUtils.validateEntity(accountBinDingVo);

		//拿到后台配置的验证码发送类型 (0:手机短信 1:邮箱验证 2:全部)
		String CODETYPE = paramsService.getValue("CODETYPE");

		//拿到绑定类型   1:手机账号  2:邮箱账号
		Integer type = accountBinDingVo.getType();
		//拿到绑定账号
		String accountNumber = accountBinDingVo.getAccountNumber();

		if(CODETYPE .equals("2")){

			if(type == 1){//发送手机验证码

				SendCode sendCode = new SendCode();
				sendCode.setUserName(accountNumber);
				Result result = sendSms(sendCode);

				return result;

			} else if (type == 2) {//发送邮箱验证码

				SendCode sendCode = new SendCode();
				sendCode.setUserName(accountNumber);

				Result result = sendEmail(sendCode);

				return result;
			}
		}


		if (CODETYPE .equals("0") && type == 1){//手机验证码
			SendCode sendCode = new SendCode();
			sendCode.setUserName(accountNumber);
			Result result = sendSms(sendCode);

			return result;
		}

		if (CODETYPE .equals("1") && type == 2){//邮箱验证码
			SendCode sendCode = new SendCode();
			sendCode.setUserName(accountNumber);

			Result result = sendEmail(sendCode);

			return result;
		}

		return new Result().ok(null);
	}




	@PostMapping("saveUserEmailOrMobileByUserId")
	@ApiOperation("绑定用户账号")
	@Login
	public Result saveUserEmailOrMobileByUserId(@LoginUser UserEntity user ,@RequestBody AccountBinDingVo accountBinDingVo) throws MessagingException {

	  //拿到验证码
		String code = accountBinDingVo.getCode();
		if(code == null || code.equals("")){
			return new Result().error("请填写验证码");
		}
		boolean b = commonService.verifySmsCode(accountBinDingVo.getAccountNumber(), accountBinDingVo.getCode());
		if(!b){
			return new Result().error("验证码有误!");
		}

		//拿到绑定账号的类型  1:手机账号  2:邮箱账号
		Integer type = accountBinDingVo.getType();
		if(type == 1 || type == 2){//查询手机账号是否已被绑定
			Map<String,Object> map = new HashMap<>();
			map.put("type",type.toString());
			map.put("accountNumber",accountBinDingVo.getAccountNumber());

			UserEntity queryaccountnumber = userService.queryaccountnumber(map);

			if(queryaccountnumber != null){
				return new Result().error("该账号已被绑定,请更换账号后重新绑定!");
			}

			//拿到用户ID
			Long userId = user.getId();

			UserEntity userEntity = new UserEntity();
			userEntity.setId(userId);
			if(type == 1){

				userEntity.setMobile(accountBinDingVo.getAccountNumber());
			} else if (type == 2) {
				userEntity.setEmail(accountBinDingVo.getAccountNumber());
			}

			userService.updateById(userEntity);
			return new Result().ok(null);

		}

		return new Result().ok(null);
	}


	/**
	 * 发送邮件
	 * @param reqBody
	 * @return
	 */
	public Result sendEmail(SendCode reqBody) throws MessagingException {
		String smsCode = (String) redisService.get(RedisKeys.SMS_CODE,reqBody.getUserName());
		if (StrUtil.isBlank(smsCode)) {
			smsCode = SmsUtils.getRandomInt(4);
			redisService.set(RedisKeys.SMS_CODE,reqBody.getUserName(),smsCode,5 * 60);
		}
		EmailConfig emailConfig = sysParamsService.getValueObject(Constant.EMAIL_CONFIG_KEY, EmailConfig.class);
		MailSmtpConfig config = MailSmtpConfig
				.builder()
				.port(emailConfig.getEmailPort())
				.fromAddress(emailConfig.getUserName())
				.smtpServer(emailConfig.getSmtpServer())
				.username(emailConfig.getUserName())
				.password(emailConfig.getPassWord())
				.build();
		MailFactory.put("email",config);
		MailClient mail = MailFactory.createMailClient("email");
		MailMessage message = MailMessage.Builder()
				//收件人地址，此处可以为字符串的单个地址也可以为一个List<String>的群发地址
				//例如  .mailAddress (new ArrayList<String>().add("XXXXXXXXX@qq.com"))
				.mailAddress (reqBody.getUserName())
				// 邮件的标题
				.title("绑定验证码")
				// 邮件的文字正文，但是如果发送html邮件时候，大多数的邮件服务商会隐藏掉body的信息
				.body("您正在进行账号绑定操作,本次操作验证码为:"+smsCode+",如非您本人操作请前往应用程序修改密码或联系客服!本验证码5分钟内有效!")
				.build();
		mail.send(message);
		return new Result();
	}





	/**
	 * 发送短信
	 * @param reqBody
	 * @return
	 */
	public Result sendSms(SendCode reqBody) {
		String smsCode = (String) redisService.get(RedisKeys.SMS_CODE,reqBody.getUserName());
		if (StrUtil.isBlank(smsCode)) {
			smsCode = SmsUtils.getRandomInt(4);
			redisService.set(RedisKeys.SMS_CODE,reqBody.getUserName(),smsCode,5 * 60);
		}
		String is_yanshi = sysParamsService.getValue("IS_YANSHI");
		if(StrUtil.isBlank(is_yanshi)){
			is_yanshi = "0";
		}
		if(is_yanshi.equals("1")){
			return new Result().error("演示环境,验证码为:"+smsCode);
		}else{
			String KEY = Constant.SMS_CONFIG_KEY;
			SmsFactory.createSmsBlend(config,KEY);
			SmsBlend smsBlend = SmsFactory.getSmsBlend(KEY);
			SmsResponse smsResponse = smsBlend.sendMessage(reqBody.getUserName(), smsCode);
		}

		return new Result();
	}

}


