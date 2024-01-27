package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.modules.Cami.dto.CamiDTO;
import io.renren.common.modules.Cami.excel.CamiExcel;
import io.renren.common.modules.Cami.service.CamiService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * 卡密表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-15
 */
@RestController
@RequestMapping("Cami/cami")
@Api(tags="卡密表")
public class CamiController {
    @Autowired
    private CamiService camiService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("Cami:cami:page")
    public Result<PageData<CamiDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<CamiDTO> page = camiService.page(params);

        return new Result<PageData<CamiDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("Cami:cami:info")
    public Result<CamiDTO> get(@PathVariable("id") Long id){
        CamiDTO data = camiService.get(id);

        return new Result<CamiDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("Cami:cami:save")
    public Result save(@RequestBody CamiDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        //拿到需要生成得数量
        Integer number = dto.getNumber();

        if(number < 1){
            return new Result().error("生成数量错误!");
        }
        for (int i = 0; i < number; i++) {
            CamiDTO camiDTO = new CamiDTO();

            //生成卡密号
            String randomStr = getRandomStr(20);

            camiDTO.setPrice(dto.getPrice());
            camiDTO.setSpecification(dto.getSpecification());
            camiDTO.setType(dto.getType());
            camiDTO.setStatus(dto.getStatus());
            camiDTO.setTime(new Date());
            camiDTO.setCamiCode(randomStr);
            camiService.save(camiDTO);
        }



        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("Cami:cami:update")
    public Result update(@RequestBody CamiDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        camiService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("Cami:cami:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        camiService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("Cami:cami:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<CamiDTO> list = camiService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, CamiExcel.class);
    }


    /**
     * 获取随机字符串，由数字、大小写字母组成
     * @param bytes：生成的字符串的位数
     * @return
     * @author
     */
    public static String getRandomStr(int bytes){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < bytes; i++) {
            //随机判断判断该字符是数字还是字母
            String choice = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(choice)) {
                //随机判断是大写字母还是小写字母
                int start = random.nextInt(2) % 2 == 0 ? 65 : 97;
                sb.append((char) (start + random.nextInt(26)));
            } else if ("num".equalsIgnoreCase(choice)) {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getRandomStr(20));
    }

}