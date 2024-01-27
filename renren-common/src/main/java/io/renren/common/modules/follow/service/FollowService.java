package io.renren.common.modules.follow.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.follow.dto.FollowDTO;
import io.renren.common.modules.follow.entity.FollowEntity;

/**
 * 关注表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-12-01
 */
public interface FollowService extends CrudService<FollowEntity, FollowDTO> {

    FollowEntity queryFromIdAndToId(Long fromUserId, Long toUserId);
}