package io.renren.common.modules.circleclass.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.circleclass.dto.CircleClassDTO;
import io.renren.common.modules.circleclass.entity.CircleClassEntity;

import java.util.List;

/**
 * 圈子分类
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
public interface CircleClassService extends CrudService<CircleClassEntity, CircleClassDTO> {

    List<CircleClassEntity> queryCircleClass();

}