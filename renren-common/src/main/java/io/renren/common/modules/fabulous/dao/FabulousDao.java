package io.renren.common.modules.fabulous.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.fabulous.entity.FabulousEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 点赞记录表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Mapper
public interface FabulousDao extends BaseDao<FabulousEntity> {
	
}