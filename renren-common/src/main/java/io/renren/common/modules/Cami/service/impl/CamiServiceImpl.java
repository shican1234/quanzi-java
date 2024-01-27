package io.renren.common.modules.Cami.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.modules.Cami.dao.CamiDao;
import io.renren.common.modules.Cami.dto.CamiDTO;
import io.renren.common.modules.Cami.entity.CamiEntity;
import io.renren.common.modules.Cami.service.CamiService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 卡密表
 *
 * @author tiechui 706902861@qq.com
 * @since 1.0.0 2024-01-15
 */
@Service
public class CamiServiceImpl extends CrudServiceImpl<CamiDao, CamiEntity, CamiDTO> implements CamiService {

    @Override
    public QueryWrapper<CamiEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CamiEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        wrapper.orderByDesc("time");

        return wrapper;
    }


    @Override
    public CamiEntity getCamiByCode(String camiCode) {

        return baseDao.getCamiByCode(camiCode);
    }
}