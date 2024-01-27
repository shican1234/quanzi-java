package io.renren.common.modules.post.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.dto.*;
import io.renren.common.modules.post.entity.PostEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 帖子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Mapper
public interface PostDao extends BaseDao<PostEntity> {

    List<PostEntity> selectIndex(PageDTO pageDTO);

    void unLike(Long tid);

    void like(Long tid);

    PostEntity getPostInfo(LikeDto pageDTO);

    List<PostEntity> queryGz(PageDTO pagesUserForm);

    List<PostEntity> queryCirclePost(QueryCirclePostDTO pageDTO);

    List<PostEntity> circleTopPost(QueryCirclePostDTO pageDTO);

    List<PostEntity> myCirListPost(QueryCirclePostDTO pageDTO);

    List<PostEntity> queryPostListByUserId(PostListByUserIdDTO pagesUserForm);

    List<PostEntity> queryMyPostList(GetPostListByTypeDTO pagesUserForm);

    List<PostEntity> queryMyLikePostList(GetPostListByTypeDTO pagesUserForm);

    List<PostEntity> searchPost(SearchDTO searchDTO);
}