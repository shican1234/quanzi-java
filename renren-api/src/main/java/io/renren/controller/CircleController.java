package io.renren.controller;

import cn.hutool.core.util.StrUtil;
import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.cf.annotation.PreventRepeat;
import io.renren.common.common.CommonService;
import io.renren.common.dto.*;
import io.renren.common.modules.circle.dto.CircleDTO;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circle.service.CircleService;
import io.renren.common.modules.circleclass.dto.CircleClassDTO;
import io.renren.common.modules.circleclass.entity.CircleClassEntity;
import io.renren.common.modules.circleclass.service.CircleClassService;
import io.renren.common.modules.circleuser.dto.CircleUserDTO;
import io.renren.common.modules.circleuser.entity.CircleUserEntity;
import io.renren.common.modules.circleuser.service.CircleUserService;
import io.renren.common.modules.post.entity.PostEntity;
import io.renren.common.modules.post.service.PostService;
import io.renren.common.modules.token.entity.TokenEntity;
import io.renren.common.modules.token.service.TokenService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.redis.SysParamsKeys;
import io.renren.common.service.SysParamsService;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/circle")
@Api(tags="圈子接口")
@CrossOrigin
public class CircleController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CircleClassService circleClassService;
    @Autowired
    private CircleService circleService;
    @Autowired
    private CircleUserService circleUserService;
    @Autowired
    private SysParamsService sysParamsService;
    @PostMapping("queryCircleClass")
    @ApiOperation("查询所有圈子分类")
    public Result queryCircleClass(){
        List<CircleClassEntity> list = circleClassService.queryCircleClass();
        return new Result().ok(list);
    }


    @PostMapping("addCircle")
    @ApiOperation("创建圈子")
    @Login
    public Result addCircle(@LoginUser UserEntity user, @RequestBody AddCircleDTO reqBody){
        ValidatorUtils.validateEntity(reqBody);


        String circleName = reqBody.getCircleName();
        CircleEntity circleEntityName =  circleService.queryByName(circleName);
        if(circleEntityName != null){
            return new Result().error("圈子名称重复");
        }
        CircleEntity circleEntity = new CircleEntity(reqBody);
        circleEntity.setUserId(user.getId());
        circleService.insert(circleEntity);
        CircleUserEntity circleUserEntity = new CircleUserEntity();
        circleUserEntity.setCircleId(circleEntity.getId());
        circleUserEntity.setUserId(user.getId());
        circleUserEntity.setCreateTime(new Date());
        circleUserEntity.setType(3);
        circleUserService.insert(circleUserEntity);
        return new Result().ok(circleEntity);
    }


    @PostMapping("queryCircleByClass")
    @ApiOperation("查询所有圈子根据分类分类")
    public Result queryCircleByClass(@RequestBody CircleByClassDto circleByClassDto){
        circleByClassDto.setPage(circleByClassDto.getPage() * 10);
        Long cid = circleByClassDto.getCid();
        if(cid <= 0){
            circleByClassDto.setCid(null);
        }
        if(cid == 0){
            circleByClassDto.setHotOk(1);
        }
        List<CircleEntity> list = circleService.queryCircleByClass(circleByClassDto);
        return new Result().ok(list);
    }
    @PostMapping("queryCircleById")
    @ApiOperation("根据圈子ID查询圈子详情")
    public Result queryCircleById(@RequestBody GetByIdDto dto){
        CircleEntity circleEntity = circleService.selectById(dto.getId());
        //查询圈主详情
        UserEntity userEntity = userService.selectById(circleEntity.getUserId());
        circleEntity.setNickName(userEntity.getNickName());
        circleEntity.setAvatar(userEntity.getAvatar());
        //查询圈子帖子数
        int postCount = postService.countByCid(dto.getId());
        //查询加入人数
        int peopleCount =circleUserService.countByCid(dto.getId());
        circleEntity.setPostCount(postCount);
        circleEntity.setPeopleCount(peopleCount);
        return new Result().ok(circleEntity);
    }

    @PostMapping("isJoinCircle")
    @ApiOperation("查看当前登入用户是否进入了这个圈子")
    @Login
    public Result isJoinCircle(@LoginUser UserEntity user,@RequestBody GetByIdDto dto){
        boolean isJoin = false;
        CircleUserEntity circleEntity =  circleUserService.isJoinCircle(user.getId(),dto.getId());
        if(circleEntity != null){
            isJoin = true;
        }
        return new Result().ok(isJoin);
    }
    @PostMapping("jionCircle")
    @ApiOperation("加入圈子")
    @Login
    public Result jionCircle(@LoginUser UserEntity user,@RequestBody GetByIdDto dto){
        //先判断是否已加入这个圈子
        CircleUserEntity circleEntity =  circleUserService.isJoinCircle(user.getId(),dto.getId());
        if(circleEntity != null){
            return new Result().error("您已加入该圈子,无需重复加入!");
        }
        return commonService.jionCircle(user,dto);
    }

    @PostMapping("myCirList")
    @ApiOperation("查看我加入的圈子列表")
    @Login
    public Result myCirList(@LoginUser UserEntity user,@RequestBody PageDTO pageDTO){
        pageDTO.setPage(pageDTO.getPage() * 10);
        pageDTO.setUserId(user.getId());
        List<CircleEntity> list = circleUserService.myCirList(pageDTO);
        return new Result().ok(list);
    }


    @PostMapping("getRoleInCircleByUser")
    @ApiOperation("查询当前登入用户对于该圈子是什么角色")
    @Login
    public Result getRoleInCircleByUser(@LoginUser UserEntity user,@RequestBody GetByIdDto dto){
        CircleUserEntity circleUserEntity = circleUserService.isJoinCircle(user.getId(), dto.getId());

        return new Result().ok(circleUserEntity);
    }


    @PostMapping("queryUserListByCircle")
    @ApiOperation("查看指定圈子用户列表")
    @Login
    public Result queryUserListByCircle(@LoginUser UserEntity user,@RequestBody QueryUserListByCircleDTO pageDTO){
        CircleUserEntity circleEntity =  circleUserService.isJoinCircle(user.getId(),pageDTO.getCircleId());
        if(circleEntity == null){
            return new Result().error("请先加入圈子,暂无权限查看!");
        }
        pageDTO.setPage(pageDTO.getPage() * 10);
        List<CircleUserDTO> list = circleUserService.queryUserListByCircle(pageDTO);
        return new Result().ok(list);
    }
    @PostMapping("removeCircleUser")
    @ApiOperation("移除圈子用户")
    @Login
    public Result removeCircleUser(@LoginUser UserEntity user,@RequestBody RemoveCircleUserDto pageDTO){
        CircleUserEntity circleEntity =  circleUserService.isJoinCircle(user.getId(),pageDTO.getCircleId());
        if(circleEntity == null){
            return new Result().error("请先加入圈子,暂无权限!");
        }
        if(circleEntity.getType() < 2){
            return new Result().error("暂无权限!");
        }
        if(user.getId().equals(pageDTO.getUserId())){
            return new Result().error("不能移除自己");
        }
        CircleUserEntity joinCircle = circleUserService.isJoinCircle(pageDTO.getUserId(), pageDTO.getCircleId());
        circleUserService.deleteById(joinCircle.getId());
        return new Result();
    }

    @PostMapping("setAdminCircleUser")
    @ApiOperation("设置圈子管理员/移除圈子管理员")
    @Login
    public Result setAdminCircleUser(@LoginUser UserEntity user,@RequestBody RemoveCircleUserDto pageDTO){
        CircleUserEntity circleEntity =  circleUserService.isJoinCircle(user.getId(),pageDTO.getCircleId());
        if(circleEntity == null){
            return new Result().error("请先加入圈子,暂无权限!");
        }
        if(circleEntity.getType() != 3){
            return new Result().error("暂无权限!");
        }
        if(user.getId().equals(pageDTO.getUserId())){
            return new Result().error("不能操作自己");
        }
        CircleUserEntity joinCircle = circleUserService.isJoinCircle(pageDTO.getUserId(), pageDTO.getCircleId());
        joinCircle.setType(joinCircle.getType() == 2 ? 0 : 2);
        circleUserService.updateById(joinCircle);
        UserEntity userEntity = userService.selectById(pageDTO.getUserId());
        CircleUserDTO circleUserDTO = new CircleUserDTO();
        BeanUtils.copyProperties(joinCircle,circleUserDTO);
        circleUserDTO.setAvatar(userEntity.getAvatar());
        circleUserDTO.setNickName(userEntity.getNickName());
        circleUserDTO.setGender(circleUserDTO.getGender());
        return new Result().ok(circleUserDTO);
    }

    @PostMapping("updateCircle")
    @ApiOperation("修改圈子")
    @Login
    public Result updateCircle(@LoginUser UserEntity user, @RequestBody UpdateCircleDTO reqBody){
        ValidatorUtils.validateEntity(reqBody);
        CircleUserEntity circleUserEntity =  circleUserService.isJoinCircle(user.getId(),reqBody.getId());
        if(circleUserEntity == null){
            return new Result().error("请先加入圈子,暂无权限!");
        }
        if(circleUserEntity.getType() != 3){
            return new Result().error("暂无权限!");
        }
        String circleName = reqBody.getCircleName();
        CircleEntity circleEntityName =  circleService.queryByName(circleName);
        if(circleEntityName != null && !reqBody.getId().equals(circleEntityName.getId())){
            return new Result().error("圈子名称重复");
        }
        CircleEntity circleEntity = new CircleEntity();
        circleEntity.setId(reqBody.getId());
        circleEntity.setCircleBackground(reqBody.getCircleBackground());
        circleEntity.setCircleName(reqBody.getCircleName());
        circleEntity.setCircleSculpture(reqBody.getCircleSculpture());
        circleEntity.setCircleIntroduce(reqBody.getCircleIntroduce());
        circleService.updateById(circleEntity);
        return new Result().ok(circleEntity);
    }


    @PostMapping("getCreatCircleListByUserId")
    @ApiOperation("查看指定用户创建的圈子列表")
    @Login
    public Result getCreatCircleListByUserId(@LoginUser UserEntity user,@RequestBody PageDTO pageDTO){
        pageDTO.setPage(pageDTO.getPage() * 10);
        pageDTO.setUserId(pageDTO.getUserId());
        List<CircleEntity> list = circleUserService.getCreatCircleListByUserId(pageDTO);
        return new Result().ok(list);
    }

    @PostMapping("queryJoinCirclelistByUserId")
    @ApiOperation("查看指定用户加入的圈子列表,不包含创建的")
    @Login
    public Result queryJoinCirclelistByUserId(@LoginUser UserEntity user,@RequestBody PageDTO pageDTO){
        pageDTO.setPage(pageDTO.getPage() * 10);
        pageDTO.setUserId(pageDTO.getUserId());
        List<CircleEntity> list = circleUserService.queryJoinCirclelistByUserId(pageDTO);
        return new Result().ok(list);
    }

    @PostMapping("hot")
    @ApiOperation("热门圈子")
    public Result hot(){
        List<CircleEntity> list = circleService.hotCircleList();
        return new Result().ok(list);
    }

    @PostMapping("exit")
    @ApiOperation("退出圈子")
    @Login
    public Result exit(@LoginUser UserEntity user,@RequestBody RemoveCircleUserDto pageDTO){
        CircleEntity circleEntity = circleService.selectById(pageDTO.getCircleId());
        if(circleEntity.getUserId().equals(user.getId())){
            return new Result().error("圈主不允许退出圈子");
        }
        CircleUserEntity joinCircle = circleUserService.isJoinCircle(user.getId(), pageDTO.getCircleId());
        circleUserService.deleteById(joinCircle.getId());
        return new Result();
    }



    @PostMapping("hotBlogger")
    @ApiOperation("热门博主")
    @Login
    public Result hotBlogger(@LoginUser UserEntity user,@RequestBody PageDTO pageDTO){
        pageDTO.setPage(pageDTO.getPage() * 10);
        List<UserEntity> list = userService.hotBlogger(pageDTO);
        return new Result().ok(list);
    }
}
