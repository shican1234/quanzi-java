package io.renren.modules.comment;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.post.dto.PostDTO;
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
import io.renren.common.modules.comment.dto.CommentDTO;
import io.renren.common.modules.comment.excel.CommentExcel;
import io.renren.common.modules.comment.service.CommentService;
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
 * 评论
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-30
 */
@RestController
@RequestMapping("comment/comment")
@Api(tags="评论")
public class CommentController {
    @Autowired
    private CommentService commentService;
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
    @RequiresPermissions("comment:comment:page")
    public Result<PageData<CommentDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("orderField","create_time");
        params.put("order","desc");
        PageData<CommentDTO> page = commentService.page(params);
        if(CollUtil.isNotEmpty(page.getList())){
            for (CommentDTO circleDTO : page.getList()) {
                UserEntity userEntity = userService.selectById(circleDTO.getCommentUserId());
                if(userEntity != null){
                    circleDTO.setAvatar(userEntity.getAvatar());
                    circleDTO.setNickName(userEntity.getNickName());
                }
            }
        }
        return new Result<PageData<CommentDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("comment:comment:info")
    public Result<CommentDTO> get(@PathVariable("id") Long id){
        CommentDTO data = commentService.get(id);

        return new Result<CommentDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("comment:comment:save")
    public Result save(@RequestBody CommentDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        commentService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("comment:comment:update")
    public Result update(@RequestBody CommentDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        commentService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("comment:comment:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        commentService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("comment:comment:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CommentDTO> list = commentService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CommentExcel.class);
    }

}