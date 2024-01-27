package io.renren.common.modules.follow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.follow.dao.FollowDao;
import io.renren.common.modules.follow.dto.FollowDTO;
import io.renren.common.modules.follow.entity.FollowEntity;
import io.renren.common.modules.follow.service.FollowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 关注表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-01
 */
@Service
public class FollowServiceImpl extends CrudServiceImpl<FollowDao, FollowEntity, FollowDTO> implements FollowService {

    @Override
    public QueryWrapper<FollowEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<FollowEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public FollowEntity queryFromIdAndToId(Long fromUserId, Long toUserId) {
        QueryWrapper<FollowEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FollowEntity::getFromUser, fromUserId)
                .eq(FollowEntity::getToUser, toUserId);
        FollowEntity one = baseDao.selectOne(queryWrapper);
        return one;
    }
}