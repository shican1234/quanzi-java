package io.renren.common.modules.follow.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.follow.entity.FollowEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关注表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-01
 */
@Mapper
public interface FollowDao extends BaseDao<FollowEntity> {
	
}