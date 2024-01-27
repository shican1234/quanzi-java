package io.renren.common.modules.circleuser.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.dto.PageDTO;
import io.renren.common.dto.QueryUserListByCircleDTO;
import io.renren.common.modules.circle.dto.CircleDTO;
import io.renren.common.modules.circle.entity.CircleEntity;
import io.renren.common.modules.circleuser.dto.CircleUserDTO;
import io.renren.common.modules.circleuser.entity.CircleUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 圈子人员
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-08
 */
@Mapper
public interface CircleUserDao extends BaseDao<CircleUserEntity> {

    List<CircleEntity> myCirList(PageDTO pageDTO);

    List<CircleUserDTO> queryUserListByCircle(QueryUserListByCircleDTO pageDTO);

    List<CircleEntity> getCreatCircleListByUserId(PageDTO pageDTO);

    List<CircleEntity> queryJoinCirclelistByUserId(PageDTO pageDTO);
}