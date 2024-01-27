package io.renren.common.modules.comment.service;

import io.renren.common.dto.LikeDto;
import io.renren.common.service.CrudService;
import io.renren.common.modules.comment.dto.CommentDTO;
import io.renren.common.modules.comment.entity.CommentEntity;
import io.renren.common.vo.CommentListVo;

import java.util.List;

/**
 * 评论
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-30
 */
public interface CommentService extends CrudService<CommentEntity, CommentDTO> {

    List<CommentListVo> getComment(LikeDto reqBody);

    void like(Long commentId);

    void unLike(Long commentId);

    void deleteByPostId(Long id);
}