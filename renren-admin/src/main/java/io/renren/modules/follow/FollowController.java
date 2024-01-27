package io.renren.modules.follow;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.comment.dto.CommentDTO;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.common.modules.follow.dto.FollowDTO;
import io.renren.common.modules.follow.excel.FollowExcel;
import io.renren.common.modules.follow.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 关注表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-01
 */
@RestController
@RequestMapping("follow/follow")
@Api(tags="关注表")
public class FollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;
    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("follow:follow:page")
    public Result<PageData<FollowDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<FollowDTO> page = followService.page(params);
        if(CollUtil.isNotEmpty(page.getList())){
            for (FollowDTO circleDTO : page.getList()) {
                UserEntity userEntity = userService.selectById(circleDTO.getFromUser());
                if(userEntity != null){
                    circleDTO.setFromAvatar(userEntity.getAvatar());
                    circleDTO.setFromName(userEntity.getNickName());
                }

                UserEntity userEntityTo = userService.selectById(circleDTO.getToUser());
                if(userEntityTo != null){
                    circleDTO.setToAvatar(userEntityTo.getAvatar());
                    circleDTO.setToName(userEntityTo.getNickName());
                }
            }
        }
        return new Result<PageData<FollowDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("follow:follow:info")
    public Result<FollowDTO> get(@PathVariable("id") Long id){
        FollowDTO data = followService.get(id);

        return new Result<FollowDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("follow:follow:save")
    public Result save(@RequestBody FollowDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        followService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("follow:follow:update")
    public Result update(@RequestBody FollowDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        followService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("follow:follow:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        followService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("follow:follow:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<FollowDTO> list = followService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, FollowExcel.class);
    }

}