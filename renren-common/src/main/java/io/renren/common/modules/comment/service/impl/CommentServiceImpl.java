package io.renren.common.modules.comment.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.dto.LikeDto;
import io.renren.common.modules.fabulous.entity.FabulousEntity;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.comment.dao.CommentDao;
import io.renren.common.modules.comment.dto.CommentDTO;
import io.renren.common.modules.comment.entity.CommentEntity;
import io.renren.common.modules.comment.service.CommentService;
import io.renren.common.vo.CommentListVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 评论
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-30
 */
@Service
public class CommentServiceImpl extends CrudServiceImpl<CommentDao, CommentEntity, CommentDTO> implements CommentService {

    @Override
    public QueryWrapper<CommentEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String commentUserId = (String)params.get("commentUserId");

        QueryWrapper<CommentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(commentUserId), "comment_user_id", commentUserId);

        return wrapper;
    }


    @Override
    public List<CommentListVo> getComment(LikeDto reqBody) {
        return baseDao.getComment(reqBody);
    }

    @Override
    public void like(Long commentId) {
        baseDao.like(commentId);
    }

    @Override
    public void unLike(Long commentId) {
        baseDao.unLike(commentId);
    }

    @Override
    public void deleteByPostId(Long id) {
        QueryWrapper<CommentEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CommentEntity::getTid,id);
        baseDao.delete(wrapper);
    }
}