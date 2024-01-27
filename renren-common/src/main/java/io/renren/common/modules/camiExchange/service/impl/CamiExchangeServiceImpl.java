package io.renren.common.modules.camiExchange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.modules.camiExchange.dao.CamiExchangeDao;
import io.renren.common.modules.camiExchange.dto.CamiExchangeDTO;
import io.renren.common.modules.camiExchange.entity.CamiExchangeEntity;
import io.renren.common.modules.camiExchange.service.CamiExchangeService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 卡密兑换记录表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-16
 */
@Service
public class CamiExchangeServiceImpl extends CrudServiceImpl<CamiExchangeDao, CamiExchangeEntity, CamiExchangeDTO> implements CamiExchangeService {

    @Override
    public QueryWrapper<CamiExchangeEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String userId = (String)params.get("userId");
        String camiCode = (String)params.get("camiCode");

        QueryWrapper<CamiExchangeEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(userId), "user_id", userId);
        wrapper.eq(StringUtils.isNotBlank(camiCode), "cami_code", camiCode);

        wrapper.orderByDesc("time");

        return wrapper;
    }


    @Override
    public List<CamiExchangeEntity> queryCamiExchangeList(Map<String, Object> map) {

        return baseDao.queryCamiExchangeList(map);
    }
}