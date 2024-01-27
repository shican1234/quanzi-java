package io.renren.common.modules.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.dto.*;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.post.dao.PostDao;
import io.renren.common.modules.post.dto.PostDTO;
import io.renren.common.modules.post.entity.PostEntity;
import io.renren.common.modules.post.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 帖子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Service
public class PostServiceImpl extends CrudServiceImpl<PostDao, PostEntity, PostDTO> implements PostService {

    @Override
    public QueryWrapper<PostEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");
        String status = (String)params.get("status");
        String userId = (String)params.get("userId");
        String postClass = (String)params.get("postClass");
        String type = (String)params.get("type");
        String fileType = (String)params.get("fileType");
        String payType = (String)params.get("payType");

        QueryWrapper<PostEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);
        wrapper.eq(StringUtils.isNotBlank(userId), "user_id", userId);
        wrapper.eq(StringUtils.isNotBlank(postClass), "post_class", postClass);
        wrapper.eq(StringUtils.isNotBlank(type), "type", type);
        wrapper.eq(StringUtils.isNotBlank(fileType), "file_type", fileType);
        wrapper.eq(StringUtils.isNotBlank(payType), "pay_type", payType);

        return wrapper;
    }


    @Override
    public List<PostEntity> selectIndex(PageDTO pageDTO) {
        return baseDao.selectIndex(pageDTO);
    }

    @Override
    public void like(Long tid) {
        baseDao.like(tid);
    }

    @Override
    public void unLike(Long tid) {
        baseDao.unLike(tid);
    }

    @Override
    public PostEntity getPostInfo(LikeDto pageDTO) {
        return baseDao.getPostInfo(pageDTO);
    }

    @Override
    public List<PostEntity> queryGz(PageDTO pagesUserForm) {
        return baseDao.queryGz(pagesUserForm);
    }

    @Override
    public int countByCid(Long id) {
        QueryWrapper<PostEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PostEntity::getCircleId,id);
        Long aLong = baseDao.selectCount(wrapper);
        if(aLong == null ){
            return 0;
        }
        return aLong.intValue();
    }

    @Override
    public List<PostEntity> queryCirclePost(QueryCirclePostDTO pageDTO) {
        return baseDao.queryCirclePost(pageDTO);
    }

    @Override
    public List<PostEntity> circleTopPost(QueryCirclePostDTO pageDTO) {
        return baseDao.circleTopPost(pageDTO);
    }

    @Override
    public List<PostEntity> myCirListPost(QueryCirclePostDTO pageDTO) {
        return baseDao.myCirListPost(pageDTO);
    }

    @Override
    public List<PostEntity> queryPostListByUserId(PostListByUserIdDTO pagesUserForm) {
        return baseDao.queryPostListByUserId(pagesUserForm);
    }

    @Override
    public List<PostEntity> queryMyPostList(GetPostListByTypeDTO pagesUserForm) {
        return baseDao.queryMyPostList(pagesUserForm);
    }

    @Override
    public List<PostEntity> queryMyLikePostList(GetPostListByTypeDTO pagesUserForm) {
        return baseDao.queryMyLikePostList(pagesUserForm);
    }

    @Override
    public List<PostEntity> searchPost(SearchDTO searchDTO) {
        return baseDao.searchPost(searchDTO);
    }

}