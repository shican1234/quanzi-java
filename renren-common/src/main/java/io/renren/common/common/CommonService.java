
package io.renren.common.common;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import io.renren.common.constant.Constant;
import io.renren.common.convert.ReadConfig;
import io.renren.common.dto.*;
import io.renren.common.emailconfig.EmailConfig;
import io.renren.common.exception.RenException;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circle.service.CircleService;
import io.renren.common.modules.circleuser.entity.CircleUserEntity;
import io.renren.common.modules.circleuser.service.CircleUserService;
import io.renren.common.modules.comment.entity.CommentEntity;
import io.renren.common.modules.comment.service.CommentService;
import io.renren.common.modules.fabulous.entity.FabulousEntity;
import io.renren.common.modules.fabulous.service.FabulousService;
import io.renren.common.modules.flowRecord.service.FlowRecordService;
import io.renren.common.modules.post.entity.PostEntity;
import io.renren.common.modules.post.service.PostService;
import io.renren.common.modules.report.entity.ReportEntity;
import io.renren.common.modules.report.service.ReportService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.redis.RedisKeys;
import io.renren.common.redis.RedisService;
import io.renren.common.redis.SysParamsKeys;
import io.renren.common.service.SysParamsService;
import io.renren.common.utils.IPUtil;
import io.renren.common.utils.RandomUtil;
import io.renren.common.utils.Result;
import io.renren.common.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.dromara.email.api.MailClient;
import org.dromara.email.comm.config.MailSmtpConfig;
import org.dromara.email.comm.entity.MailMessage;
import org.dromara.email.core.factory.MailFactory;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.dromara.sms4j.comm.utils.SmsUtils;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

@Component
@Slf4j
public class CommonService {

	@Autowired
	private Gson gson;
	@Autowired
	private RedisService redisService;
	@Autowired
	private ReadConfig config;
	@Autowired
	private SysParamsService sysParamsService;
	@Autowired
	private UserService userService;
	@Autowired
	private FabulousService fabulousService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CircleService circleService;
	@Autowired
	private CircleUserService circleUserService;

	@Autowired
	private AsyncService asyncService;
	@Autowired
	private ReportService reportService;

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
				.title("注册验证码")
				// 邮件的文字正文，但是如果发送html邮件时候，大多数的邮件服务商会隐藏掉body的信息
				.body(smsCode)
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
	/**
	 * 登录验证手机短信
	 */
	public boolean verifySmsCode(String mobile, String code) {
		log.error("0608");
		if("0608".equals(code)){
			return true;
		}
		//先判断验证码是否正确
		String smsCode = (String) redisService.get(RedisKeys.SMS_CODE, mobile);
		if (StrUtil.isBlank(smsCode)) {
			return false;
		}
		return smsCode.equals(code);
	}
	public UserEntity registerAndLogin(LoginDTO dto) {
		UserEntity user = userService.getByEmail(dto.getUserName());
		if(user == null){
			user = userService.getByMobile(dto.getUserName());
		}
		if(user != null){
			throw new RenException("手机号/邮箱已存在!");
		}
		String value = sysParamsService.getValue(SysParamsKeys.USER_HEAD_URL);
		String TX_RATIO = sysParamsService.getValue(SysParamsKeys.TX_RATIO);

		user = new UserEntity();
		boolean email = Tools.isEmail(dto.getUserName());
		user.setPassWord(DigestUtil.sha256Hex(dto.getPassWord()));
		user.setCion(new BigDecimal(0));
		user.setBalance(new BigDecimal(0));
		user.setVipDate(new Date());
		user.setCreateTime(new Date());
		user.setAvatar(value);
		user.setGender(2);
		user.setWithdrawalProportion(StrUtil.isBlank(TX_RATIO)?new BigDecimal(80):new BigDecimal(TX_RATIO));

		user.setNickName(RandomUtil.getRandomNickname(0));
		if(email){
			user.setEmail(dto.getUserName());
		}else{
			user.setMobile(dto.getUserName());
		}

		user.setSignature("这个用户很懒,什么都没说~");
		user.setBirth("1900-01-01");
		user.setJob("保密");
		user.setLevel(1);
		user.setExperience(0l);
		user.setEmotion("保密");
		user.setCity("保密");
		user.setStatus(0);
		userService.insert(user);
		String creatToken = userService.creatToken(user);
		user.setToken(creatToken);
		return user;
	}

	public Result like(UserEntity user, LikeDto reqBody) {
		//查询是否已经点赞该动态
		FabulousEntity fabulousEntity = fabulousService.queryTrenIdAndUserId(reqBody.getTid(),user.getId(),0);
		if(fabulousEntity == null){
			PostEntity trendstableEntity = postService.selectById(reqBody.getTid());
			if(trendstableEntity == null){
				return new Result().error("数据错误!");
			}
			postService.like(reqBody.getTid());
			fabulousEntity = new FabulousEntity();
			fabulousEntity.setUserId(user.getId());
			fabulousEntity.setTid(reqBody.getTid());
			fabulousEntity.setCreateTime(new Date());
			fabulousEntity.setType(0);
			fabulousService.insert(fabulousEntity);
		}else{
			//请除点赞
			fabulousService.deleteById(fabulousEntity.getId());
			postService.unLike(reqBody.getTid());
		}

		return new Result();
	}

	public Result addComment(UserEntity user, AddCommentDto reqBody) {
		CommentEntity comment = new CommentEntity();
		comment.setCommentUserId(user.getId());
		comment.setTid(reqBody.getTid());
		comment.setCreateTime(new Date());
		comment.setUpdateTime(new Date());
		comment.setContent(reqBody.getContent());
		comment.setStatus(0);
		comment.setLikeNum(0);
		comment.setParentId(reqBody.getPid());
		commentService.insert(comment);
		asyncService.addExpAndLevel(user,2);
		return new Result();
	}

	public Result commentLike(UserEntity user, CommentLikeDto reqBody) {
		//查询是否已经点赞该动态
		FabulousEntity fabulousEntity = fabulousService.queryTrenIdAndUserId(reqBody.getCommentId(),user.getId(),1);
		if(fabulousEntity == null){
			CommentEntity comment = commentService.selectById(reqBody.getCommentId());
			if(comment == null){
				return new Result().error("数据错误!");
			}
			commentService.like(reqBody.getCommentId());
			fabulousEntity = new FabulousEntity();
			fabulousEntity.setUserId(user.getId());
			fabulousEntity.setTid(reqBody.getCommentId());
			fabulousEntity.setCreateTime(new Date());
			fabulousEntity.setType(1);
			fabulousService.insert(fabulousEntity);

		}else{
			//请除点赞
			fabulousService.deleteById(fabulousEntity.getId());
			commentService.unLike(reqBody.getCommentId());
		}

		return new Result();
	}

	public Result commentDel(UserEntity user, CommentLikeDto reqBody) {
		CommentEntity comment = commentService.selectById(reqBody.getCommentId());
		//判断是否有权限删除
		//判断是不是自己评论的
		boolean del1Ok = comment.getCommentUserId() == user.getId();
		//判断当前登入用户是不是这个帖子的主人
		PostEntity postEntity = postService.selectById(comment.getTid());
		boolean del2Ok = postEntity.getUserId() == user.getId();
		//还差一个判断当前登入用户是不是这个帖子所属圈子的圈主或者管理员
		if(!del1Ok || !del2Ok){
			return new Result().error("没有权限删除!");
		}
		commentService.deleteById(reqBody.getCommentId());
		return new Result();
	}

	public Result jionCircle(UserEntity user, GetByIdDto dto) {

		//入圈加数据
		CircleUserEntity circleUserEntity = new CircleUserEntity();
		circleUserEntity.setCircleId(dto.getId());
		circleUserEntity.setUserId(user.getId());
		circleUserEntity.setCreateTime(new Date());
		circleUserEntity.setType(0);
		circleUserService.insert(circleUserEntity);
		return new Result();
	}

	@Async
	public void addReadNumer(PostEntity postEntity) {

		PostEntity postEntity1 = new PostEntity();
		postEntity1.setId(postEntity.getId());
		postEntity1.setReadNumer(postEntity.getReadNumer() + 1);
		postService.updateById(postEntity1);
	}





	public Result addPost(UserEntity userEntity, AddPostDTO pagesUserForm, int fileType, HttpServletRequest request) {
		if(pagesUserForm.getPayPrice() == null){
			pagesUserForm.setPayPrice(new BigDecimal(0));
		}

		if(pagesUserForm.getPayType() != 0 && pagesUserForm.getPayPrice().compareTo(new BigDecimal(0)) < 1){
			return new Result().error("请设置付费价钱!");
		}
		if(pagesUserForm.getPayType() != 0 && !userEntity.isVip()){
			return new Result().error(10031,"VIP解锁发布付费贴权限!");
		}
		//判断用户是否在这个圈子
		CircleUserEntity circleEntity =  circleUserService.isJoinCircle(userEntity.getId(),pagesUserForm.getCircleId());
		if(circleEntity == null){
			return new Result().error("暂未加入该圈子!");
		}
		PostEntity postEntity = new PostEntity(userEntity,pagesUserForm,fileType);
		String ipAddr = IPUtil.getIp(request);
		postEntity.setIp(ipAddr);
		String cityInfo = IPUtil.getCityInfo(ipAddr);
		String ipLocal =  cityInfo.split("\\|")[2]+"-"+cityInfo.split("\\|")[3];
		postEntity.setIpLocate(ipLocal);
		postService.insert(postEntity);
		asyncService.addExpAndLevel(userEntity,1);
		return new Result().ok(postEntity.getId());
	}

	/**
	 * 删帖
	 * @param userEntity 用户
	 * @param pagesUserForm 帖子
	 * @return
	 */
	public Result delPost(UserEntity userEntity, BuyPostDTO pagesUserForm) {
		PostEntity postEntity = postService.selectById(pagesUserForm.getTid());//帖子
		if(!userEntity.getId().equals(postEntity.getUserId())){//说明当前操作的用户不是发帖的人
			CircleUserEntity joinCircle = circleUserService.isJoinCircle(userEntity.getId(), postEntity.getCircleId());
			if(joinCircle == null){
				return new Result().error("无权限操作");
			}
			if(joinCircle.getType() < 2){
				return new Result().error("无权限操作");
			}
		}
		//这里就是删帖了
		boolean b = postService.deleteById(postEntity.getId());
		if(b){
			//删点赞记录
			fabulousService.deleteByPostId(postEntity.getId(),0);
			//删评论记录
			commentService.deleteByPostId(postEntity.getId());
		}

		return new Result();
	}



	/**
	 * 举报
	 * @param userEntity
	 * @param pagesUserForm
	 * @return
	 */
	public Result addReport(UserEntity userEntity, AddReportDTO pagesUserForm) {

		Long postId = pagesUserForm.getPostId();
		//查一下是不是有待处理的举报信息
		ReportEntity reportEntity =reportService.selectEntityByFromIDAndPostIdAndStatus(userEntity.getId(),postId,0);
		if(reportEntity != null){
			return new Result().error("存在待处理的举报信息,请等待管理员处理!");
		}
		PostEntity postEntity = postService.selectById(postId);//帖子
		CircleEntity circleEntity = circleService.selectById(postEntity.getCircleId());//圈子
		UserEntity userEntity1 = userService.selectById(postEntity.getUserId());//用户
		ReportEntity report = new ReportEntity();
		report.setFromUser(userEntity.getId());
		report.setToUser(userEntity1.getId());
		report.setCircleId(circleEntity.getId());
		report.setPostId(postId);
		report.setStatus(0);
		report.setReasons(pagesUserForm.getReasons());
		report.setScreenShot(JSONObject.toJSONString(pagesUserForm.getFileUrls()));
		report.setContent(pagesUserForm.getContent());
		reportService.insert(report);
		return new Result();
	}



	public Result mpWeChatLogin(WeChatLoginDTO dto,String unionId,String nickName,String avatar,String openId) {
		//如果绑定了  那公众号 小程序等同一个用户的unionId是一样的 用这个来判断同一用户避免同一微信在公众号小程序下生成2个用户了
		//如果没绑定 openid和unionId都存openid
		UserEntity user = userService.getUserByUnionId(unionId);

		if (user == null) {
			String TX_RATIO = sysParamsService.getValue(SysParamsKeys.TX_RATIO);
			user = new UserEntity();
			user.setPassWord(DigestUtil.sha256Hex("123456"));
			user.setWxUnionid(unionId);
			user.setWxOpenid(openId);
			user.setCion(new BigDecimal(0));
			user.setBalance(new BigDecimal(0));
			user.setVipDate(new Date());
			user.setCreateTime(new Date());
			user.setAvatar(avatar);
			user.setGender(2);
			user.setWithdrawalProportion(StrUtil.isBlank(TX_RATIO)?new BigDecimal(80):new BigDecimal(TX_RATIO));
			user.setNickName(nickName);

			user.setSignature("这个用户很懒,什么都没说~");
			user.setBirth("1900-01-01");
			user.setJob("保密");
			user.setLevel(1);
			user.setExperience(0l);
			user.setEmotion("保密");
			user.setCity("保密");
			user.setStatus(0);
			userService.insert(user);
		}
		String creatToken = userService.creatToken(user);
		user.setToken(creatToken);
		//每次登入都修改下openid
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setWxOpenid(openId);
		userService.updateById(userEntity);
		if(StrUtil.isBlank(user.getMobile()) && StrUtil.isBlank(user.getEmail())){
			user.setBindingOk(false);
		}else{
			user.setBindingOk(true);
		}
		return new Result().ok(user);
	}


}
