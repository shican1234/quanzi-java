package io.renren.common.modules.circleclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.circleclass.dao.CircleClassDao;
import io.renren.common.modules.circleclass.dto.CircleClassDTO;
import io.renren.common.modules.circleclass.entity.CircleClassEntity;
import io.renren.common.modules.circleclass.service.CircleClassService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 圈子分类
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-05
 */
@Service
public class CircleClassServiceImpl extends CrudServiceImpl<CircleClassDao, CircleClassEntity, CircleClassDTO> implements CircleClassService {

    @Override
    public QueryWrapper<CircleClassEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CircleClassEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public List<CircleClassEntity> queryCircleClass() {
        QueryWrapper<CircleClassEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(CircleClassEntity::getSorts);
        return baseDao.selectList(wrapper);
    }
}