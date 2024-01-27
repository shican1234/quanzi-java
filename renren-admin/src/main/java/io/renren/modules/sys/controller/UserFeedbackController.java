package io.renren.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.modules.userfeedback.dto.UserFeedbackDTO;
import io.renren.common.modules.userfeedback.excel.UserFeedbackExcel;
import io.renren.common.modules.userfeedback.service.UserFeedbackService;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-20
 */
@RestController
@RequestMapping("userfeedback/userfeedback")
@Api(tags="")
public class UserFeedbackController {
    @Autowired
    private UserFeedbackService userFeedbackService;
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
    @RequiresPermissions("userfeedback:userfeedback:page")
    public Result<PageData<UserFeedbackDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<UserFeedbackDTO> page = userFeedbackService.page(params);

        List<UserFeedbackDTO> list = page.getList();
        for (UserFeedbackDTO userFeedbackDTO: list) {
            //拿到用户ID
            Long userId = userFeedbackDTO.getUserId();
            //根据用户ID查询用户数据
            UserEntity userByUserId = userService.getUserByUserId(userId);

            //拿到用户昵称
            String memberNichen = userByUserId.getNickName();
            userFeedbackDTO.setMemberNichen(memberNichen);
            userFeedbackDTO.setAvatar(userByUserId.getAvatar());

            //拿到图片集合
            String img = userFeedbackDTO.getImg();

            if(img != null){
                JSONArray jsonArray = JSON.parseArray(img);
                List<String> images = new ArrayList<>();


                for (int i = 0; i < jsonArray.size(); i++) {
                    String o = jsonArray.get(i).toString();

                    images.add(o);
                }
                //设置反馈图片
                userFeedbackDTO.setImages(images);

            }
        }

        return new Result<PageData<UserFeedbackDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("userfeedback:userfeedback:info")
    public Result<UserFeedbackDTO> get(@PathVariable("id") Long id){
        UserFeedbackDTO data = userFeedbackService.get(id);

        return new Result<UserFeedbackDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("userfeedback:userfeedback:save")
    public Result save(@RequestBody UserFeedbackDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        userFeedbackService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("userfeedback:userfeedback:update")
    public Result update(@RequestBody UserFeedbackDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        String replyContent = dto.getReplyContent();
        if (replyContent != null && !replyContent.equals("")){
            dto.setType(1);
            dto.setReplyTime(new Date());
        }else {
            dto.setType(0);
        }

        userFeedbackService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("userfeedback:userfeedback:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        userFeedbackService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("userfeedback:userfeedback:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<UserFeedbackDTO> list = userFeedbackService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, UserFeedbackExcel.class);
    }

}