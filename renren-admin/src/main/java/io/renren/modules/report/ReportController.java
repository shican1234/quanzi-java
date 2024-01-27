package io.renren.modules.report;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.annotation.LogOperation;
import io.renren.common.common.AsyncService;
import io.renren.common.constant.Constant;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circleuser.dto.CircleUserDTO;
import io.renren.common.modules.post.dto.PostDTO;
import io.renren.common.modules.post.entity.PostEntity;
import io.renren.common.modules.report.entity.ReportEntity;
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
import io.renren.common.modules.report.dto.ReportDTO;
import io.renren.common.modules.report.excel.ReportExcel;
import io.renren.common.modules.report.service.ReportService;
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
 * 举报表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-21
 */
@RestController
@RequestMapping("report/report")
@Api(tags="举报表")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;
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
    @RequiresPermissions("report:report:page")
    public Result<PageData<ReportDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        params.put("orderField","create_time");
        params.put("order","desc");
        PageData<ReportDTO> page = reportService.page(params);
        if(CollUtil.isNotEmpty(page.getList())){
            for (ReportDTO circleDTO : page.getList()) {
                UserEntity userEntity = userService.selectById(circleDTO.getFromUser());
                if(userEntity != null){
                    circleDTO.setFromAvatar(userEntity.getAvatar());
                    circleDTO.setFromName(userEntity.getNickName());
                }
                UserEntity userEntity1 = userService.selectById(circleDTO.getToUser());
                if(userEntity1 != null){
                    circleDTO.setToAvatar(userEntity1.getAvatar());
                    circleDTO.setToName(userEntity1.getNickName());
                }
            }
        }
        return new Result<PageData<ReportDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("report:report:info")
    public Result<ReportDTO> get(@PathVariable("id") Long id){
        ReportDTO data = reportService.get(id);

        return new Result<ReportDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("report:report:save")
    public Result save(@RequestBody ReportDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        reportService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("report:report:update")
    public Result update(@RequestBody ReportDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        reportService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("report:report:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        reportService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("report:report:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<ReportDTO> list = reportService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, ReportExcel.class);
    }
    @PostMapping("pass")
    @ApiOperation("举报通过")
    @LogOperation("举报通过")
    public Result pass(@RequestBody ReportDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
        ReportEntity reportEntity = reportService.selectById(dto.getId());
        if(reportEntity == null){
            return new Result().error("数据错误");
        }
        if(reportEntity.getStatus() != 0){
            return new Result().error("该数据已处理");
        }
        ReportEntity postEntity1 = new ReportEntity();
        postEntity1.setId(dto.getId());
        postEntity1.setStatus(dto.getStatus());
        reportService.updateById(postEntity1);
        UserEntity fromUser = userService.selectById(reportEntity.getFromUser());
        UserEntity toUser = userService.selectById(reportEntity.getToUser());
       
        return new Result();
    }
}