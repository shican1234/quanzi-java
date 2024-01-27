package io.renren.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Json;
import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.userfeedback.dto.UserFeedbackDTO;
import io.renren.common.modules.userfeedback.entity.UserFeedbackEntity;
import io.renren.common.modules.userfeedback.service.UserFeedbackService;
import io.renren.common.utils.Result;
import io.renren.common.vo.FeedbackReturnVo;
import io.renren.vo.SolveVo;
import io.renren.vo.UserFeedbackVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户反馈接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/member")
@Api(tags="用户反馈接口")
public class UserFeedbackController {

	@Autowired
	private UserFeedbackService userFeedbackService;


	@PostMapping("saveFeedback")
	@ApiOperation("用户反馈")
	@Login
	public Result saveFeedback(@LoginUser UserEntity user, @RequestBody UserFeedbackVo userFeedbackVo) throws Exception {

		UserFeedbackEntity userFeedback = new UserFeedbackEntity();
		userFeedback.setUserId(user.getId());
		userFeedback.setFeedbackContent(userFeedbackVo.getFeedbackContent());
		userFeedback.setFeedbackTime(new Date());
		userFeedback.setImg(JSONObject.toJSONString(userFeedbackVo.getFileUrls()));
		userFeedback.setContactInformation(userFeedback.getContactInformation());
		userFeedback.setStatus(2);
		userFeedback.setType(0);
		userFeedback.setUserName(userFeedbackVo.getUserName());
		userFeedbackService.insert(userFeedback);
		return new Result();
	}

	@PostMapping("queryFeedbackByUserId")
	@ApiOperation("根据用户ID查询用户的反馈信息")
	@Login
	public Result<List<FeedbackReturnVo>> queryFeedbackByUserId(@LoginUser UserEntity user) throws Exception {

		//拿到用户ID
		Long UserId = user.getId();
		//根据用户ID查询用户的反馈信息
		List<UserFeedbackEntity> flowRecordByUserId = userFeedbackService.getFlowRecordByUserId(UserId);

		List<FeedbackReturnVo> feedbackReturnVoList = new ArrayList<>();

		for (UserFeedbackEntity userFeedbackEntity: flowRecordByUserId) {
			FeedbackReturnVo feedbackReturnVo = new FeedbackReturnVo();
			//拿到用户的反馈内容
			String feedbackContent = userFeedbackEntity.getFeedbackContent();
			//设置反馈内容
			feedbackReturnVo.setContent(feedbackContent);
			//判断内容的长度
			int length = feedbackContent.length();
			if(length > 12){
				//将内容的前12个文字提取为反馈标题
				String substring = feedbackContent.substring(0, 12);
				//设置反馈标题
				feedbackReturnVo.setTitle(substring+"...");
			}else {
				//设置反馈标题
				feedbackReturnVo.setTitle(feedbackContent);
			}

			//拿到回复状态(0:未回复  1:已回复)
			Integer type = userFeedbackEntity.getType();
			//设置反馈时间
			feedbackReturnVo.setCreateTime(userFeedbackEntity.getFeedbackTime().toLocaleString());
			//判断回复状态
			if(type == 0){
				//设置回复状态
				feedbackReturnVo.setState("0");
				//设置类型
				feedbackReturnVo.setStateName("待回复");
			}else {
				//设置回复状态
				feedbackReturnVo.setState("1");
				//设置类型
				feedbackReturnVo.setStateName("已回复");
				//设置回复时间
				feedbackReturnVo.setUpdateTime(userFeedbackEntity.getReplyTime().toLocaleString());
				//设置回复内容
				feedbackReturnVo.setReply(userFeedbackEntity.getReplyContent());
			}

			//拿到图片集合
			String img = userFeedbackEntity.getImg();

			if(img != null && !img.equals("")){

				JSONArray jsonArray = JSON.parseArray(img);
				List<Map<String, Object>> images = new ArrayList<>();


				for (int i = 0; i < jsonArray.size(); i++) {
					Map<String,Object> map = new HashMap<>();
					Object o = jsonArray.get(i);
					map.put("url",o);

					images.add(map);
				}
				//设置反馈图片
				feedbackReturnVo.setImages(images);

				feedbackReturnVo.setSolve(userFeedbackEntity.getStatus().toString());
				feedbackReturnVo.setId(userFeedbackEntity.getId());

			}
			feedbackReturnVoList.add(feedbackReturnVo);
		}

		return new Result<List<FeedbackReturnVo>>().ok(feedbackReturnVoList);
	}

	@PostMapping("updateFeedbackById")
	@ApiOperation("修改用户反馈是否解决")
	@Login
	public Result updateFeedbackById(@LoginUser UserEntity user, @RequestBody SolveVo solveVo) throws Exception {

		//拿到反馈ID
		Long id = solveVo.getId();

		UserFeedbackDTO userFeedbackDTO = userFeedbackService.get(id);

		if(userFeedbackDTO.getStatus() != 2){
			return new Result().ok(null);
		}

		//拿到解决类型
		Integer solve = solveVo.getSolve();

		//封装修改数据
		UserFeedbackDTO userFeedback = new UserFeedbackDTO();
		userFeedback.setStatus(solve);
		userFeedback.setId(id);
		userFeedbackService.update(userFeedback);

		return new Result().ok(null);
	}

	@PostMapping("queryFeedbackById")
	@ApiOperation("根据反馈ID查询详细反馈信息")
	@Login
	public Result<FeedbackReturnVo> queryFeedbackById(@LoginUser UserEntity user, @RequestBody UserFeedbackVo userFeedbackVo) throws Exception {

		//拿到反馈ID
		Long id = userFeedbackVo.getId();

		UserFeedbackDTO userFeedbackDTO = userFeedbackService.get(id);


			FeedbackReturnVo feedbackReturnVo = new FeedbackReturnVo();
			//拿到用户的反馈内容
			String feedbackContent = userFeedbackDTO.getFeedbackContent();
			//设置反馈内容
			feedbackReturnVo.setContent(feedbackContent);
			//判断内容的长度
			int length = feedbackContent.length();
			if(length > 12){
				//将内容的前12个文字提取为反馈标题
				String substring = feedbackContent.substring(0, 12);
				//设置反馈标题
				feedbackReturnVo.setTitle(substring+"...");
			}else {
				//设置反馈标题
				feedbackReturnVo.setTitle(feedbackContent);
			}

			//拿到回复状态(0:未回复  1:已回复)
			Integer type = userFeedbackDTO.getType();
			//设置反馈时间
			feedbackReturnVo.setCreateTime(userFeedbackDTO.getFeedbackTime().toLocaleString());
			//判断回复状态
			if(type == 0){
				//设置回复状态
				feedbackReturnVo.setState("0");
				//设置类型
				feedbackReturnVo.setStateName("待回复");
			}else {
				//设置回复状态
				feedbackReturnVo.setState("1");
				//设置类型
				feedbackReturnVo.setStateName("已回复");
				//设置回复时间
				feedbackReturnVo.setUpdateTime(userFeedbackDTO.getReplyTime().toLocaleString());
				//设置回复内容
				feedbackReturnVo.setReply(userFeedbackDTO.getReplyContent());
			}

			//拿到图片集合
			String img = userFeedbackDTO.getImg();

			if(img != null && !img.equals("")){

				JSONArray jsonArray = JSON.parseArray(img);
				List<Map<String, Object>> images = new ArrayList<>();


				for (int i = 0; i < jsonArray.size(); i++) {
					Map<String,Object> map = new HashMap<>();
					Object o = jsonArray.get(i);
					map.put("url",o);

					images.add(map);
				}
				//设置反馈图片
				feedbackReturnVo.setImages(images);

				feedbackReturnVo.setSolve(userFeedbackDTO.getStatus().toString());
				feedbackReturnVo.setId(userFeedbackDTO.getId());

			}


		return new Result<FeedbackReturnVo>().ok(feedbackReturnVo);
	}
}


