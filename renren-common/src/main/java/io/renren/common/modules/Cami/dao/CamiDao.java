package io.renren.common.modules.Cami.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.Cami.entity.CamiEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 卡密表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-15
 */
@Mapper
public interface CamiDao extends BaseDao<CamiEntity> {

    /**
     * 根据卡密号查询卡密
     */
    CamiEntity getCamiByCode(String camiCode);
	
}