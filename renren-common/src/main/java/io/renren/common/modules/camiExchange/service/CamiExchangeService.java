package io.renren.common.modules.camiExchange.service;

import io.renren.common.modules.camiExchange.dto.CamiExchangeDTO;
import io.renren.common.modules.camiExchange.entity.CamiExchangeEntity;
import io.renren.common.service.CrudService;

import java.util.List;
import java.util.Map;

/**
 * 卡密兑换记录表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-16
 */
public interface CamiExchangeService extends CrudService<CamiExchangeEntity, CamiExchangeDTO> {

    List<CamiExchangeEntity> queryCamiExchangeList(Map<String,Object> map);

}