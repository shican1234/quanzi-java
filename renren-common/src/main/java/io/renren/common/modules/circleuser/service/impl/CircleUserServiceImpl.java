package io.renren.common.modules.circleuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.dto.GetByIdDto;
import io.renren.common.dto.PageDTO;
import io.renren.common.dto.QueryUserListByCircleDTO;
import io.renren.common.modules.circle.dto.CircleDTO;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.circleuser.dao.CircleUserDao;
import io.renren.common.modules.circleuser.dto.CircleUserDTO;
import io.renren.common.modules.circleuser.entity.CircleUserEntity;
import io.renren.common.modules.circleuser.service.CircleUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 圈子人员
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-08
 */
@Service
public class CircleUserServiceImpl extends CrudServiceImpl<CircleUserDao, CircleUserEntity, CircleUserDTO> implements CircleUserService {

    @Override
    public QueryWrapper<CircleUserEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String circleId = (String)params.get("circleId");
        String userId = (String)params.get("userId");

        QueryWrapper<CircleUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(circleId), "circle_id", circleId);
        wrapper.eq(StringUtils.isNotBlank(userId), "user_id", userId);

        return wrapper;
    }


    @Override
    public int countByCid(Long id) {
        QueryWrapper<CircleUserEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CircleUserEntity::getCircleId,id);
        Long aLong = baseDao.selectCount(wrapper);
        if(aLong == null){
            return 0;
        }
        return aLong.intValue();
    }

    @Override
    public CircleUserEntity isJoinCircle(Long userId, Long circleId) {
        QueryWrapper<CircleUserEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CircleUserEntity::getCircleId,circleId);
        wrapper.lambda().eq(CircleUserEntity::getUserId,userId);
        return baseDao.selectOne(wrapper);
    }

    @Override
    public List<CircleEntity> myCirList(PageDTO pageDTO) {
        return baseDao.myCirList(pageDTO);
    }

    @Override
    public List<CircleUserDTO> queryUserListByCircle(QueryUserListByCircleDTO pageDTO) {
        return baseDao.queryUserListByCircle(pageDTO);
    }

    @Override
    public List<CircleEntity> getCreatCircleListByUserId(PageDTO pageDTO) {
        return baseDao.getCreatCircleListByUserId(pageDTO);
    }

    @Override
    public List<CircleEntity> queryJoinCirclelistByUserId(PageDTO pageDTO) {
        return baseDao.queryJoinCirclelistByUserId(pageDTO);
    }
}