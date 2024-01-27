package io.renren.common.modules.camiExchange.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.camiExchange.entity.CamiExchangeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 卡密兑换记录表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-16
 */
@Mapper
public interface CamiExchangeDao extends BaseDao<CamiExchangeEntity> {

    List<CamiExchangeEntity> queryCamiExchangeList(Map<String,Object> map);
	
}