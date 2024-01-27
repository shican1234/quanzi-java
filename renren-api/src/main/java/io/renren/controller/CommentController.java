package io.renren.controller;

import cn.hutool.core.util.StrUtil;
import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.cf.annotation.PreventRepeat;
import io.renren.common.common.CommonService;
import io.renren.common.dto.AddCommentDto;
import io.renren.common.dto.CommentLikeDto;
import io.renren.common.dto.LikeDto;
import io.renren.common.dto.PageDTO;
import io.renren.common.modules.comment.entity.CommentEntity;
import io.renren.common.modules.comment.service.CommentService;
import io.renren.common.modules.post.entity.PostEntity;
import io.renren.common.modules.post.service.PostService;
import io.renren.common.modules.token.entity.TokenEntity;
import io.renren.common.modules.token.service.TokenService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.vo.CommentListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
@Api(tags="评论接口")
@CrossOrigin
public class CommentController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CommentService commentService;

    //根据帖子ID查询评论列表
    @PostMapping("getComment")
    @ApiOperation(value="根据帖子ID查询评论列表")
    @PreventRepeat
    public Result getComment(@RequestBody LikeDto reqBody,HttpServletRequest request){
        ValidatorUtils.validateEntity(reqBody);
        //判断当前是否有登入用户
        Long userId = 0L;
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        if(StrUtil.isNotBlank(token)){
            TokenEntity byToken = tokenService.getByToken(token);
            userId = byToken.getUserId();
            reqBody.setUserId(userId);
        }

        List<CommentListVo> list = commentService.getComment(reqBody);
        boolean deleOk = false;

        //看当前登入用户是不是这个帖子的主人或者圈子主人 或者圈子管理员 是主人就可以删评论
        PostEntity postEntity = postService.selectById(reqBody.getTid());
        if(postEntity.getUserId() == userId){//这里还没有写圈子等 所以这里只写了判断是不是发帖的人
            deleOk = true;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("deleOk",deleOk);
        map.put("list",list);
        return new Result().ok(map);
    }
    @Login
    @PostMapping("addComment")
    @ApiOperation(value="发布评论")
    @PreventRepeat
    public Result addComment(@LoginUser UserEntity user, @RequestBody AddCommentDto reqBody){
        ValidatorUtils.validateEntity(reqBody);
        return commonService.addComment(user,reqBody);
    }
    @Login
    @PostMapping("commentLike")
    @ApiOperation(value="点赞/取消点赞")
    @PreventRepeat
    public Result commentLike(@LoginUser UserEntity user, @RequestBody CommentLikeDto reqBody){
        ValidatorUtils.validateEntity(reqBody);
        return commonService.commentLike(user,reqBody);
    }

    @Login
    @PostMapping("commentDel")
    @ApiOperation(value="删除评论")
    @PreventRepeat
    public Result commentDel(@LoginUser UserEntity user, @RequestBody CommentLikeDto reqBody){
        ValidatorUtils.validateEntity(reqBody);
        return commonService.commentDel(user,reqBody);
    }
}
