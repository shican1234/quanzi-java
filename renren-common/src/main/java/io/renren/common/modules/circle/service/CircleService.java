package io.renren.common.modules.circle.service;

import io.renren.common.dto.CircleByClassDto;
import io.renren.common.dto.SearchDTO;
import io.renren.common.service.CrudService;
import io.renren.common.modules.circle.dto.CircleDTO;
import io.renren.common.modules.circle.entity.CircleEntity;

import java.util.List;

/**
 * 圈子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
public interface CircleService extends CrudService<CircleEntity, CircleDTO> {

    /**
     * 根据圈子名查找圈子
     * @param circleName
     * @return
     */
    CircleEntity queryByName(String circleName);

    List<CircleEntity> queryCircleByClass(CircleByClassDto circleByClassDto);

    List<CircleEntity> hotCircleList();


    List<CircleEntity> selectByUserId(Long id);

    List<CircleEntity> searchCircle(SearchDTO searchDTO);
}