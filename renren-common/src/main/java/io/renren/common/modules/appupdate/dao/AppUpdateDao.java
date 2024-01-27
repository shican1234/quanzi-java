package io.renren.common.modules.appupdate.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.dto.UpdateDTO;
import io.renren.common.modules.appconfig.entity.AppConfigEntity;
import io.renren.common.modules.appupdate.entity.AppUpdateEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 更新配置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-20
 */
@Mapper
public interface AppUpdateDao extends BaseDao<AppUpdateEntity> {

    List<AppUpdateEntity> queryUpdateInfo(UpdateDTO dto);
}