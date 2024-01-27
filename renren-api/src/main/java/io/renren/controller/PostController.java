package io.renren.controller;

import cn.hutool.core.util.StrUtil;
import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.cf.annotation.PreventRepeat;
import io.renren.common.common.CommonService;
import io.renren.common.dto.*;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circle.service.CircleService;
import io.renren.common.modules.circleuser.entity.CircleUserEntity;
import io.renren.common.modules.circleuser.service.CircleUserService;
import io.renren.common.modules.flowRecord.service.FlowRecordService;
import io.renren.common.modules.post.entity.PostEntity;
import io.renren.common.modules.post.service.PostService;
import io.renren.common.modules.token.entity.TokenEntity;
import io.renren.common.modules.token.service.TokenService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@Api(tags="帖子接口")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CircleService circleService;
    @Autowired
    private CircleUserService circleUserService;
    @Autowired
    private FlowRecordService recordService;
    @Autowired
    private FlowRecordService flowRecordService;

    @PostMapping("index")
    @ApiOperation("首页帖子列表")
    /**
     * 这个接口没用用登入注解是因为首页数据得所有人看到  所以这里需要单独弄下 并且首页只查圈外的 非付费贴
     */
    public Result index(@RequestBody PageDTO pageDTO, HttpServletRequest request) {
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        pageDTO.setUserId(0l);
        if (StrUtil.isNotBlank(token)) {
            TokenEntity byToken = tokenService.getByToken(token);
            if (byToken != null) {
                pageDTO.setUserId(byToken.getUserId());
            }
        }
        int page = pageDTO.getPage() * 10;
        pageDTO.setPage(page);
        List<PostEntity> list = postService.selectIndex(pageDTO);

        return new Result().ok(list);
    }

    @PostMapping("queryCirclePost")
    @ApiOperation("圈子内帖子列表")
    @Login
    public Result queryCirclePost(@LoginUser UserEntity user, @RequestBody QueryCirclePostDTO pageDTO) {
        //先看用户是不是这个圈子人
        CircleUserEntity circleUserEntity = circleUserService.isJoinCircle(user.getId(), pageDTO.getId());
        if (circleUserEntity == null) {
            return new Result().error("请先加入圈子!");
        }
        int page = pageDTO.getPage() * 10;
        pageDTO.setPage(page);
        pageDTO.setUserId(user.getId());
        Integer type = pageDTO.getType();
        if (type == 0) {
            pageDTO.setType(null);
        }
        if (type == 2) {//查询圈主ID
            CircleEntity circleEntity = circleService.selectById(pageDTO.getId());
            pageDTO.setCUid(circleEntity.getUserId());
        }
        List<PostEntity> list = postService.queryCirclePost(pageDTO);

        return new Result().ok(list);
    }

    @PostMapping("myCirListPost")
    @ApiOperation("用户加入的圈子帖子列表")
    @Login
    public Result myCirListPost(@LoginUser UserEntity user, @RequestBody QueryCirclePostDTO pageDTO) {
        int page = pageDTO.getPage() * 10;
        pageDTO.setPage(page);
        pageDTO.setUserId(user.getId());
        List<PostEntity> list = postService.myCirListPost(pageDTO);

        return new Result().ok(list);
    }

    @PostMapping("circleTopPost")
    @ApiOperation("圈子已经置顶的帖子")
    @Login
    public Result circleTopPost(@LoginUser UserEntity user, @RequestBody QueryCirclePostDTO pageDTO) {
        CircleUserEntity circleEntity = circleUserService.isJoinCircle(user.getId(), pageDTO.getId());
        if (circleEntity == null) {
            return new Result().ok(new ArrayList<>());
        }
        List<PostEntity> list = postService.circleTopPost(pageDTO);

        return new Result().ok(list);
    }

    @Login
    @PostMapping("like")
    @ApiOperation(value = "点赞/取消点赞")
    @PreventRepeat
    public Result like(@LoginUser UserEntity user, @RequestBody LikeDto reqBody) {
        ValidatorUtils.validateEntity(reqBody);
        return commonService.like(user, reqBody);
    }


    @PostMapping("getPostInfo")
    @ApiOperation("获取帖子详情")
    /**
     * 这个接口没用用登入注解是因为首页数据得所有人看到  所以这里需要单独弄下
     */
    public Result getPostInfo(@RequestBody LikeDto pageDTO, HttpServletRequest request) {
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        if (StrUtil.isBlank(token)) {
            pageDTO.setUserId(0l);
        } else {
            TokenEntity byToken = tokenService.getByToken(token);
            pageDTO.setUserId(byToken.getUserId());
        }
        PostEntity postEntity = postService.getPostInfo(pageDTO);
        if (postEntity == null) {
            return new Result().error("帖子不存在");
        }
        if (postEntity.getStatus() == 0 && !postEntity.getUserId().equals(pageDTO.getUserId())) {//如果帖子待审核并且不是当前用户的帖子
            return new Result().error("帖子不存在");
        }
        if (postEntity.getType() == 1) {//圈内贴 判断用户在不在这个圈子

            CircleUserEntity circleEntity = circleUserService.isJoinCircle(postEntity.getUserId(), postEntity.getCircleId());
            if (circleEntity == null) {
                return new Result().error("帖子不存在");
            }
        }

        commonService.addReadNumer(postEntity);
        return new Result().ok(postEntity);
    }


    @Login
    @PostMapping("queryGz")
    @ApiOperation(value = "查询我关注的人的动态")
    public Result queryGz(@LoginUser UserEntity userEntity, @RequestBody PageDTO pagesUserForm) {
        //表单校验
        ValidatorUtils.validateEntity(pagesUserForm);
        pagesUserForm.setUserId(userEntity.getId());
        pagesUserForm.setPage(pagesUserForm.getPage() * 10);
        List<PostEntity> list = postService.queryGz(pagesUserForm);

        return new Result().ok(list);
    }


    @Login
    @PostMapping("addPostByImg")
    @ApiOperation(value = "发图文贴")
    public Result addPostByImg(@LoginUser UserEntity userEntity, @RequestBody AddPostDTO pagesUserForm, HttpServletRequest request) {
        //表单校验
        ValidatorUtils.validateEntity(pagesUserForm);

        return commonService.addPost(userEntity, pagesUserForm, 0, request);
    }

    @Login
    @PostMapping("addPostByVideo")
    @ApiOperation(value = "发视频贴")
    public Result addPostByVideo(@LoginUser UserEntity userEntity, @RequestBody AddPostDTO pagesUserForm, HttpServletRequest request) {
        //表单校验
        ValidatorUtils.validateEntity(pagesUserForm);

        return commonService.addPost(userEntity, pagesUserForm, 1, request);
    }


    @Login
    @PostMapping("delPost")
    @ApiOperation(value = "删帖")
    public Result delPost(@LoginUser UserEntity userEntity, @RequestBody BuyPostDTO pagesUserForm) {
        //表单校验
        ValidatorUtils.validateEntity(pagesUserForm);

        return commonService.delPost(userEntity, pagesUserForm);
    }





    @Login
    @PostMapping("queryPostListByUserId")
    @ApiOperation(value = "查看指定用户的公开的帖子列表")
    public Result queryPostListByUserId(@LoginUser UserEntity userEntity, @RequestBody PostListByUserIdDTO pagesUserForm) {
        //表单校验
        ValidatorUtils.validateEntity(pagesUserForm);
        pagesUserForm.setPage(pagesUserForm.getPage() * 10);
        pagesUserForm.setMyUserId(userEntity.getId());
        List<PostEntity> list = postService.queryPostListByUserId(pagesUserForm);

        return new Result().ok(list);
    }

    @Login
    @PostMapping("getPostListByType")
    @ApiOperation(value = "我发的帖子列表/我点赞的帖子列表")
    public Result getPostListByType(@LoginUser UserEntity userEntity, @RequestBody GetPostListByTypeDTO pagesUserForm) {
        //表单校验

        pagesUserForm.setPage(pagesUserForm.getPage() * 10);
        pagesUserForm.setUserId(userEntity.getId());
        List<PostEntity> list = new ArrayList<>();
        if (pagesUserForm.getType() == 0) {
            list = postService.queryMyPostList(pagesUserForm);
        } else {
            list = postService.queryMyLikePostList(pagesUserForm);
        }
        return new Result().ok(list);
    }




}