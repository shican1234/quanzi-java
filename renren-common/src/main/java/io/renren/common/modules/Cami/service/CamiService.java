package io.renren.common.modules.Cami.service;

import io.renren.common.modules.Cami.dto.CamiDTO;
import io.renren.common.modules.Cami.entity.CamiEntity;
import io.renren.common.service.CrudService;

/**
 * 卡密表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-15
 */
public interface CamiService extends CrudService<CamiEntity, CamiDTO> {

    /**
     * 根据卡密号查询卡密
     */
    CamiEntity getCamiByCode(String camiCode);

}