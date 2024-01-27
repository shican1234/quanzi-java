package io.renren.common.modules.appconfig.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.modules.appconfig.dao.AppConfigDao;
import io.renren.common.modules.appconfig.dto.AppConfigDTO;
import io.renren.common.modules.appconfig.entity.AppConfigEntity;
import io.renren.common.modules.appconfig.service.AppConfigService;
import io.renren.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * APP设置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-10
 */
@Service
public class AppConfigServiceImpl extends CrudServiceImpl<AppConfigDao, AppConfigEntity, AppConfigDTO> implements AppConfigService {

    @Override
    public QueryWrapper<AppConfigEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AppConfigEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public AppConfigEntity getInfo() {

        return baseDao.getInfo();
    }
}