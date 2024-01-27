package io.renren.common.modules.fabulous.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.fabulous.dao.FabulousDao;
import io.renren.common.modules.fabulous.dto.FabulousDTO;
import io.renren.common.modules.fabulous.entity.FabulousEntity;
import io.renren.common.modules.fabulous.service.FabulousService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 点赞记录表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Service
public class FabulousServiceImpl extends CrudServiceImpl<FabulousDao, FabulousEntity, FabulousDTO> implements FabulousService {

    @Override
    public QueryWrapper<FabulousEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<FabulousEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public FabulousEntity queryTrenIdAndUserId(Long tid, Long id,Integer type) {
        QueryWrapper<FabulousEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FabulousEntity::getUserId,id);
        wrapper.lambda().eq(FabulousEntity::getTid,tid);
        wrapper.lambda().eq(FabulousEntity::getType,type);
        return baseDao.selectOne(wrapper);
    }

    @Override
    public void deleteByPostId(Long id,int type) {
        QueryWrapper<FabulousEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FabulousEntity::getTid,id);
        wrapper.lambda().eq(FabulousEntity::getType,type);
        baseDao.delete(wrapper);
    }
}