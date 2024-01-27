package io.renren.common.modules.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.report.dao.ReportDao;
import io.renren.common.modules.report.dto.ReportDTO;
import io.renren.common.modules.report.entity.ReportEntity;
import io.renren.common.modules.report.service.ReportService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 举报表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-21
 */
@Service
public class ReportServiceImpl extends CrudServiceImpl<ReportDao, ReportEntity, ReportDTO> implements ReportService {

    @Override
    public QueryWrapper<ReportEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<ReportEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public ReportEntity selectEntityByFromIDAndPostIdAndStatus(Long id, Long postId, int status) {
        QueryWrapper<ReportEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ReportEntity::getPostId,postId);
        wrapper.lambda().eq(ReportEntity::getFromUser,id);
        wrapper.lambda().eq(ReportEntity::getStatus,status);
        return baseDao.selectOne(wrapper);
    }
}