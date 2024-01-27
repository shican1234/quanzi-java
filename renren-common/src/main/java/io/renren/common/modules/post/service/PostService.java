package io.renren.common.modules.post.service;

import io.renren.common.dto.*;
import io.renren.common.service.CrudService;
import io.renren.common.modules.post.dto.PostDTO;
import io.renren.common.modules.post.entity.PostEntity;

import java.util.List;

/**
 * 帖子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
public interface PostService extends CrudService<PostEntity, PostDTO> {

    List<PostEntity> selectIndex(PageDTO pageDTO);

    void like(Long tid);

    void unLike(Long tid);

    PostEntity getPostInfo(LikeDto pageDTO);

    List<PostEntity> queryGz(PageDTO pagesUserForm);

    int countByCid(Long id);

    List<PostEntity> queryCirclePost(QueryCirclePostDTO pageDTO);

    List<PostEntity> circleTopPost(QueryCirclePostDTO pageDTO);

    List<PostEntity> myCirListPost(QueryCirclePostDTO pageDTO);

    List<PostEntity> queryPostListByUserId(PostListByUserIdDTO pagesUserForm);

    List<PostEntity> queryMyPostList(GetPostListByTypeDTO pagesUserForm);

    List<PostEntity> queryMyLikePostList(GetPostListByTypeDTO pagesUserForm);

    List<PostEntity> searchPost(SearchDTO searchDTO);
}