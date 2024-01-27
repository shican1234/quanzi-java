package io.renren.common.modules.circle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.dto.CircleByClassDto;
import io.renren.common.dto.SearchDTO;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.circle.dao.CircleDao;
import io.renren.common.modules.circle.dto.CircleDTO;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circle.service.CircleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 圈子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Service
public class CircleServiceImpl extends CrudServiceImpl<CircleDao, CircleEntity, CircleDTO> implements CircleService {

    @Override
    public QueryWrapper<CircleEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String circleName = (String)params.get("circleName");
        String userId = (String)params.get("userId");
        String circleClassId = (String)params.get("circleClassId");
        QueryWrapper<CircleEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        if(StringUtils.isNotBlank(circleName)){
            wrapper.lambda().like( CircleEntity::getCircleName, circleName);
        }
        if(StringUtils.isNotBlank(userId)){
            wrapper.lambda().eq( CircleEntity::getUserId, Long.parseLong(userId));
        }
        if(StringUtils.isNotBlank(circleClassId)){
            wrapper.lambda().eq( CircleEntity::getCircleClassId, Long.parseLong(circleClassId));
        }
        return wrapper;
    }


    @Override
    public CircleEntity queryByName(String circleName) {
        QueryWrapper<CircleEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CircleEntity::getCircleName,circleName);
        return baseDao.selectOne(wrapper);
    }

    @Override
    public List<CircleEntity> queryCircleByClass(CircleByClassDto circleByClassDto) {
        return baseDao.queryCircleByClass(circleByClassDto);
    }

    @Override
    public List<CircleEntity> hotCircleList() {

        return baseDao.hotCircleList();
    }

    @Override
    public List<CircleEntity> selectByUserId(Long id) {
        QueryWrapper<CircleEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CircleEntity::getUserId,id);
        return baseDao.selectList(wrapper);
    }

    @Override
    public List<CircleEntity> searchCircle(SearchDTO searchDTO) {
        return baseDao.searchCircle(searchDTO);
    }
}