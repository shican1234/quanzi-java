package io.renren.common.modules.report.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.report.entity.ReportEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 举报表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-21
 */
@Mapper
public interface ReportDao extends BaseDao<ReportEntity> {
	
}