package io.renren.common.modules.circle.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.dto.CircleByClassDto;
import io.renren.common.dto.SearchDTO;
import io.renren.common.modules.circle.entity.CircleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 圈子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Mapper
public interface CircleDao extends BaseDao<CircleEntity> {

    List<CircleEntity> queryCircleByClass(CircleByClassDto circleByClassDto);

    List<CircleEntity> hotCircleList();

    List<CircleEntity> searchCircle(SearchDTO searchDTO);
}