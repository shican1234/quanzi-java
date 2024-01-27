package io.renren.common.modules.commodity.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.commodity.entity.CommodityEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-11
 */
@Mapper
public interface CommodityDao extends BaseDao<CommodityEntity> {

    /**
     * 根据商品类型查询对应开启的的商品
     *
     */
    List<CommodityEntity> getCommodityByType(Integer type);
	
}