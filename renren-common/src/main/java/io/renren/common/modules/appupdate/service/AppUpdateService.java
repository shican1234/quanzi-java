package io.renren.common.modules.appupdate.service;

import io.renren.common.dto.UpdateDTO;
import io.renren.common.modules.appconfig.entity.AppConfigEntity;
import io.renren.common.service.CrudService;
import io.renren.common.modules.appupdate.dto.AppUpdateDTO;
import io.renren.common.modules.appupdate.entity.AppUpdateEntity;

import java.util.List;

/**
 * 更新配置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-20
 */
public interface AppUpdateService extends CrudService<AppUpdateEntity, AppUpdateDTO> {

    List<AppUpdateEntity> queryUpdateInfo(UpdateDTO dto);
}