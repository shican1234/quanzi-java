package io.renren.common.modules.circleclass.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.circleclass.entity.CircleClassEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 圈子分类
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Mapper
public interface CircleClassDao extends BaseDao<CircleClassEntity> {
	
}