package io.renren.common.modules.appupdate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.dto.UpdateDTO;
import io.renren.common.modules.appconfig.entity.AppConfigEntity;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.appupdate.dao.AppUpdateDao;
import io.renren.common.modules.appupdate.dto.AppUpdateDTO;
import io.renren.common.modules.appupdate.entity.AppUpdateEntity;
import io.renren.common.modules.appupdate.service.AppUpdateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 更新配置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-20
 */
@Service
public class AppUpdateServiceImpl extends CrudServiceImpl<AppUpdateDao, AppUpdateEntity, AppUpdateDTO> implements AppUpdateService {

    @Override
    public QueryWrapper<AppUpdateEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<AppUpdateEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<AppUpdateEntity> queryUpdateInfo(UpdateDTO dto) {
        return baseDao.queryUpdateInfo(dto);
    }
}