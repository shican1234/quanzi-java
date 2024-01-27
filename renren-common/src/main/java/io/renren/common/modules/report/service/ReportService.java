package io.renren.common.modules.report.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.report.dto.ReportDTO;
import io.renren.common.modules.report.entity.ReportEntity;

/**
 * 举报表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-21
 */
public interface ReportService extends CrudService<ReportEntity, ReportDTO> {

    ReportEntity selectEntityByFromIDAndPostIdAndStatus(Long id, Long postId, int status);
}