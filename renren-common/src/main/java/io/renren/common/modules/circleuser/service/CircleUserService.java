package io.renren.common.modules.circleuser.service;

import io.renren.common.dto.GetByIdDto;
import io.renren.common.dto.PageDTO;
import io.renren.common.dto.QueryUserListByCircleDTO;
import io.renren.common.modules.circle.dto.CircleDTO;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.service.CrudService;
import io.renren.common.modules.circleuser.dto.CircleUserDTO;
import io.renren.common.modules.circleuser.entity.CircleUserEntity;

import java.util.List;

/**
 * 圈子人员
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-08
 */
public interface CircleUserService extends CrudService<CircleUserEntity, CircleUserDTO> {

    int countByCid(Long id);

    CircleUserEntity isJoinCircle(Long userId,Long circleId);

    List<CircleEntity> myCirList(PageDTO pageDTO);

    List<CircleUserDTO> queryUserListByCircle(QueryUserListByCircleDTO pageDTO);

    List<CircleEntity> getCreatCircleListByUserId(PageDTO pageDTO);

    List<CircleEntity> queryJoinCirclelistByUserId(PageDTO pageDTO);
}