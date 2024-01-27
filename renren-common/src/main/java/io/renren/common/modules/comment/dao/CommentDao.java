package io.renren.common.modules.comment.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.dto.LikeDto;
import io.renren.common.modules.comment.entity.CommentEntity;
import io.renren.common.vo.CommentListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评论
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-30
 */
@Mapper
public interface CommentDao extends BaseDao<CommentEntity> {

    List<CommentListVo> getComment(LikeDto reqBody);

    void like(Long commentId);
    void unLike(Long commentId);

}