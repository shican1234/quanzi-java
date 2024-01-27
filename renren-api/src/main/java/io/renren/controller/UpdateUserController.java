package io.renren.controller;

import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.cf.annotation.PreventRepeat;
import io.renren.common.dto.FollowApiDto;
import io.renren.common.exception.ErrorCode;
import io.renren.common.modules.follow.entity.FollowEntity;
import io.renren.common.modules.follow.service.FollowService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.oss.cloud.OSSFactory;
import io.renren.common.utils.DateUtil;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.IPUtil;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.vo.ToUserInfoVo;
import io.renren.common.vo.UpdateUserVo;
import io.renren.common.vo.detailsNumberVo;
import io.renren.vo.UserPwdVo;
import io.renren.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 图片上传接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/member")
@Api(tags="用户修改接口")
public class UpdateUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;
    
  @PostMapping("getUserById")
  @ApiOperation("查询用户信息")
  @Login
  public Result<UserEntity> getUserById(@LoginUser UserEntity user) throws Exception {

	  //封装用户数据
	  user.setPassWord("");

	  //拿到用戶的VIP到期时间
	  Date vipDate = user.getVipDate();

	  boolean vip = user.isVip();
	  if(vip){
		  user.setVipType(1);
		  //格式化時間
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  String format = sdf.format(vipDate);
		  user.setBecome(format);
	  }else {
		  user.setVipType(2);
	  }

	  return new Result<UserEntity>().ok(user);
  }

  @PostMapping("getDetailsNumber")
  @ApiOperation("获取我的页面关注,粉丝等数据")
  @Login
  public Result<List<Integer>> getDetailsNumber(@LoginUser UserEntity user) throws Exception {

	  //拿到用户ID
	  Long userId = user.getId();

	  List<detailsNumberVo> detailsNumber = userService.getDetailsNumber(userId);

	  //拿到关注数量
	  Integer interestRecord = detailsNumber.get(0).getRecord();
	  //我的圈子数量
	  Integer circleRecord = detailsNumber.get(1).getRecord();
	  //我发的帖子数量
	  Integer postRecord = detailsNumber.get(2).getRecord();
	  //我发帖子点赞数量
	  Integer postLikeRecord = detailsNumber.get(3) == null ? 0 : detailsNumber.get(3).getRecord();
	  //我的评论点赞数量
	  Integer commentRecord = detailsNumber.get(4) == null ? 0 : detailsNumber.get(4).getRecord();
	  Integer fans = detailsNumber.get(5) == null ? 0 : detailsNumber.get(5).getRecord();

	  //所有点赞总数量
	  Integer total = postLikeRecord+commentRecord;

	  List<Integer> list = new ArrayList<Integer>();
	  list.add(interestRecord);
	  list.add(circleRecord);
	  list.add(postRecord);
	  list.add(total);
	  list.add(fans);

	  return new Result<List<Integer>>().ok(list);
  }


	@PostMapping("updateUserByUserId")
	@ApiOperation("修改用户信息")
	@Login
	public Result updateUserByUserId(@LoginUser UserEntity user, @RequestBody UpdateUserVo updateUserVo) throws Exception {

		//表单校验
		ValidatorUtils.validateEntity(updateUserVo);

		//修改用户数据
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setNickName(updateUserVo.getNickName());
		userEntity.setSignature(updateUserVo.getSignature());
		userEntity.setEmotion(updateUserVo.getEmotion());
		userEntity.setJob(updateUserVo.getJob());
		userEntity.setCity(updateUserVo.getCity());
		userEntity.setGender(updateUserVo.getGender());
		userEntity.setBirth(updateUserVo.getBirth());
		userEntity.setAvatar(updateUserVo.getAvatar());
		userEntity.setBgImg(updateUserVo.getBgImg());
		userEntity.setConstellation(DateUtil.getConstellation(DateUtil.formateDate(updateUserVo.getBirth()+" 00:00:00")));
		userService.updateById(userEntity);

		return new Result();
	}


	@PostMapping("updatePwd")
	@ApiOperation("修改密码")
	@Login
	public Result<String> changePwd(@LoginUser UserEntity user, @RequestBody UserPwdVo userPwdVo) throws Exception {

		String memberPwd = user.getPassWord();

		String oldPwd = DigestUtils.sha256Hex(userPwdVo.getOldPwd());

		if(!memberPwd.equals(oldPwd)){

			return new Result<String>().error("原密码错误!");

		}

		//加密新密码
		String newPwd = DigestUtils.sha256Hex(userPwdVo.getNewPwd());

		//封装用户数据
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setPassWord(newPwd);

		userService.updateById(userEntity);

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("success","1");

		return new Result<String>().okAes("修改密码成功!");
	}


	@Login
	@PostMapping("follow")
	@ApiOperation(value="关注/取消关注  点的时候没得到返回不允许在次点击")
	@PreventRepeat
	public Result follow(@LoginUser UserEntity userEntity, @RequestBody FollowApiDto followFrom){
		//表单校验
		ValidatorUtils.validateEntity(followFrom);
		Long fromUserId = userEntity.getId();
		Long toUserId = followFrom.getToUserId();
		UserEntity byId = userService.selectById(toUserId);
		if(byId == null){
			return new Result().error("被关注用户不存在!");
		}
		if(userEntity.getId() == byId.getId()){
			return new Result().error("自己不允许关注自己!");
		}
		FollowEntity followEntity = followService.queryFromIdAndToId(fromUserId,toUserId);
		if(followEntity == null){
			//说明还没有关注 那么加关注
			followEntity = new FollowEntity();
			followEntity.setToUser(toUserId);
			followEntity.setFromUser(fromUserId);
			followEntity.setCreateTime(new Date());
			followService.insert(followEntity);
		}else{
			//已经有关注了 点击此处就是取消关注 删除此条信息
			followService.deleteById(followEntity.getId());
		}
		return new Result();
	}


	@Login
	@PostMapping("isFollow")
	@ApiOperation(value="是否关注这个人")
	public Result isFollow(@LoginUser UserEntity userEntity, @RequestBody FollowApiDto followFrom){
		//表单校验
		ValidatorUtils.validateEntity(followFrom);
		boolean isFollow = false;
		FollowEntity followEntity = followService.queryFromIdAndToId(userEntity.getId(),followFrom.getToUserId());
		if(followEntity != null){
			isFollow = true;
		}
		return new Result().ok(isFollow);
	}

	@Login
	@PostMapping("getToUserInfo")
	@ApiOperation(value="查看指定用户详情")
	public Result getToUserInfo(@LoginUser UserEntity userEntity, @RequestBody FollowApiDto followFrom){
		//表单校验
		ValidatorUtils.validateEntity(followFrom);
		UserEntity toUser = userService.selectById(followFrom.getToUserId());
		ToUserInfoVo toUserInfoVo = new ToUserInfoVo();
		BeanUtils.copyProperties(toUser, toUserInfoVo);
		String cityInfo = IPUtil.getCityInfo(toUser.getLoginIp());
		String ipLocal =  cityInfo.split("\\|")[2]+"-"+cityInfo.split("\\|")[3];
		toUserInfoVo.setIpLocate(ipLocal);
		List<detailsNumberVo> detailsNumber = userService.getDetailsNumber(followFrom.getToUserId());
		Integer follow = detailsNumber.get(0).getRecord();
		Integer fans = detailsNumber.get(5).getRecord();
		toUserInfoVo.setFollow(follow);
		toUserInfoVo.setFans(fans);
		boolean isfollow = false;
		FollowEntity followEntity = followService.queryFromIdAndToId(userEntity.getId(),followFrom.getToUserId());
		if(followEntity != null){
			isfollow = true;
		}
		toUserInfoVo.setIsFollow(isfollow);
		boolean vip = toUser.isVip();
		if(vip){
			toUserInfoVo.setVipType(1);
			//格式化時間
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String format = sdf.format(toUserInfoVo.getVipDate());
			toUserInfoVo.setBecome(format);
		}else {
			toUserInfoVo.setVipType(2);
		}
		long l = cn.hutool.core.date.DateUtil.betweenMonth(toUserInfoVo.getCreateTime(), new Date(), true);
		toUserInfoVo.setCircleAge(l+"个月");
		if(l == 0){
			l = cn.hutool.core.date.DateUtil.betweenDay(toUserInfoVo.getCreateTime(), new Date(), true);
			toUserInfoVo.setCircleAge(l+"天");
		}
		return new Result().ok(toUserInfoVo);
	}
}


