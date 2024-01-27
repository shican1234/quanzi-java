package io.renren.modules.post;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.annotation.LogOperation;
import io.renren.common.common.AsyncService;
import io.renren.common.constant.Constant;
import io.renren.common.modules.circle.dto.CircleDTO;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circle.service.CircleService;
import io.renren.common.modules.circleclass.entity.CircleClassEntity;
import io.renren.common.modules.post.entity.PostEntity;
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
import io.renren.common.modules.post.dto.PostDTO;
import io.renren.common.modules.post.excel.PostExcel;
import io.renren.common.modules.post.service.PostService;
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
 * 帖子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@RestController
@RequestMapping("post/post")
@Api(tags="帖子表")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CircleService circleService;
    @Autowired
    private AsyncService asyncService;
    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("post:post:page")
    public Result<PageData<PostDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("orderField","create_time");
        params.put("order","desc");
        PageData<PostDTO> page = postService.page(params);
        if(CollUtil.isNotEmpty(page.getList())){
            for (PostDTO circleDTO : page.getList()) {
                UserEntity userEntity = userService.selectById(circleDTO.getUserId());
                if(userEntity != null){
                    circleDTO.setAvatar(userEntity.getAvatar());
                    circleDTO.setNickName(userEntity.getNickName());
                }
                //圈子名字
                CircleEntity circleEntity = circleService.selectById(circleDTO.getCircleId());
                if(circleEntity != null){
                    circleDTO.setCircleName(circleEntity.getCircleName());
                }
            }
        }
        return new Result<PageData<PostDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("post:post:info")
    public Result<PostDTO> get(@PathVariable("id") Long id){
        PostDTO data = postService.get(id);

        return new Result<PostDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("post:post:save")
    public Result save(@RequestBody PostDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        postService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("post:post:update")
    public Result update(@RequestBody PostDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        postService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("post:post:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        postService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("post:post:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<PostDTO> list = postService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, PostExcel.class);
    }
    @PostMapping("pass")
    @ApiOperation("举报通过")
    @LogOperation("举报通过")
    public Result pass(@RequestBody PostDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        PostEntity postEntity = postService.selectById(dto.getId());
        if(postEntity == null){
            return new Result().error("数据错误");
        }
        if(postEntity.getStatus() != 0){
            return new Result().error("该帖子已处理");
        }
        PostEntity postEntity1 = new PostEntity();
        postEntity1.setId(dto.getId());
        postEntity1.setStatus(dto.getStatus());
        postService.updateById(postEntity1);
        return new Result();
    }
}