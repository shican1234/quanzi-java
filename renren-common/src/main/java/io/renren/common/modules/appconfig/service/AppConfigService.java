package io.renren.common.modules.appconfig.service;

import io.renren.common.modules.appconfig.dto.AppConfigDTO;
import io.renren.common.modules.appconfig.entity.AppConfigEntity;
import io.renren.common.service.CrudService;

/**
 * APP设置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-10
 */
public interface AppConfigService extends CrudService<AppConfigEntity, AppConfigDTO> {

    AppConfigEntity getInfo();

}