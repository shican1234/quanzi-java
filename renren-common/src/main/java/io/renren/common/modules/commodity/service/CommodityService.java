package io.renren.common.modules.commodity.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.commodity.dto.CommodityDTO;
import io.renren.common.modules.commodity.entity.CommodityEntity;

import java.util.List;

/**
 * 商品表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-11
 */
public interface CommodityService extends CrudService<CommodityEntity, CommodityDTO> {

    /**
     * 根据商品类型查询对应开启的的商品
     *
     */
    List<CommodityEntity> getCommodityByType(Integer type);

}