package io.renren.common.modules.commodity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.commodity.dao.CommodityDao;
import io.renren.common.modules.commodity.dto.CommodityDTO;
import io.renren.common.modules.commodity.entity.CommodityEntity;
import io.renren.common.modules.commodity.service.CommodityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 *
 * @author chui 706902861@qq.com
 * @since 1.0.0 2023-11-11
 */
@Service
public class CommodityServiceImpl extends CrudServiceImpl<CommodityDao, CommodityEntity, CommodityDTO> implements CommodityService {

    @Autowired
    private CommodityDao commodityDao;

    @Override
    public QueryWrapper<CommodityEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CommodityEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.orderByAsc("commodity_to_sort");

        return wrapper;
    }


    @Override
    public List<CommodityEntity> getCommodityByType(Integer type) {

        List<CommodityEntity> commodityByType = commodityDao.getCommodityByType(type);

        return commodityByType;
    }
}