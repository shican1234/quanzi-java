package io.renren.common.modules.appconfig.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.appconfig.entity.AppConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * APP设置
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-10
 */
@Mapper
public interface AppConfigDao extends BaseDao<AppConfigEntity> {

    AppConfigEntity getInfo();
}