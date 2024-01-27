package io.renren.controller;

import io.renren.annotation.Login;
import io.renren.annotation.LoginUser;
import io.renren.common.modules.Cami.entity.CamiEntity;
import io.renren.common.modules.Cami.service.CamiService;
import io.renren.common.modules.camiExchange.dto.CamiExchangeDTO;
import io.renren.common.modules.camiExchange.entity.CamiExchangeEntity;
import io.renren.common.modules.camiExchange.service.CamiExchangeService;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.vo.PagesVo;
import io.renren.vo.CamiVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户提现接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cami")
@Api(tags="卡密接口")
public class CamiController {

    @Autowired
    private UserService userService;
    @Autowired
    private CamiService camiService;
    @Autowired
    private CamiExchangeService camiExchangeService;


    @PostMapping("cardExchange")
    @ApiOperation("卡密兑换接口")
    @Login
    public Result<String> cardExchange(@LoginUser UserEntity user, @RequestBody CamiVo camiVo) throws Exception {

        //表单校验
        ValidatorUtils.validateEntity(camiVo);

        //拿到卡密号
        String camiCode = camiVo.getCamiCode();

        //根据卡密号查询卡密是否存在
        CamiEntity camiByCode = camiService.getCamiByCode(camiCode);

        if(camiByCode == null){
            return new Result<String>().error("卡密错误或该卡密已被兑换!!");
        }
        //拿到卡密号得类型 (0:VIP  1:金币)
        Integer type = camiByCode.getType();
        //拿到卡密得规格
        BigDecimal specification = camiByCode.getSpecification();

        if(type == 0){//为用户加上VIP
            //为用户加上VIP到期时间
            Date date = user.rechargeVip(specification.intValue());
            //修改用户数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("vipDate",date);//VIP到期时间
            map.put("userId",user.getId());//用户ID
            userService.updateUserById(map);

        }else {//为用户加上金币
            //拿到用户金币余额
            BigDecimal cion = user.getCion();

            //计算用户余额
            BigDecimal add = cion.add(specification);
            //封装修改数据
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("cion",add);
            map.put("userId",user.getId());
            userService.updateUserById(map);
        }

        //兑换成功后修改卡密状态
        camiByCode.setStatus(1);
        camiByCode.setExchangeTime(new Date());
        camiService.updateById(camiByCode);

        //添加一天用户卡密兑换数据
        CamiExchangeDTO camiExchangeDTO = new CamiExchangeDTO();
        camiExchangeDTO.setUserId(user.getId());
        camiExchangeDTO.setCamiCode(camiCode);
        camiExchangeDTO.setPrice(camiByCode.getPrice());
        camiExchangeDTO.setSpecification(camiByCode.getSpecification());
        camiExchangeDTO.setType(type);
        camiExchangeDTO.setCamiId(camiByCode.getId());
        camiExchangeDTO.setTime(new Date());
        camiExchangeService.save(camiExchangeDTO);

        return new Result<String>().ok(null);
    }

    @PostMapping("queryCamiExchangeList")
    @ApiOperation("根据用户ID查询用户兑换记录")
    @Login
    public Result<List<CamiExchangeEntity>> queryCamiExchangeList(@LoginUser UserEntity user, @RequestBody PagesVo pagesVo) throws Exception {

        //表单校验
        ValidatorUtils.validateEntity(pagesVo);

        //拿到用户ID
        Long userId = user.getId();

        //拿到分页数据
        Integer page = pagesVo.getPage();

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("page",(page - 1) * 10);

        //根据用户ID查询用户所有得兑换记录
        List<CamiExchangeEntity> camiExchangeEntities = camiExchangeService.queryCamiExchangeList(map);

        return new Result<List<CamiExchangeEntity>>().ok(camiExchangeEntities);
    }
}


