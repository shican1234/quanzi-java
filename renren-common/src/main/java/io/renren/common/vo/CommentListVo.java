package io.renren.common.vo;


import lombok.Data;

import java.util.Date;

@Data
public class CommentListVo {
    //评论ID
    private String id;
    //是否可以删除这个评论
    private Integer owners;
    //当前登入用户是否点赞
    private Integer hasLike;
    //点赞人数
    private Integer likeNum;
    //点赞人的头像
    private String avatarUrl;
    //点赞的人的昵称
    private String nickName;
    //评论内容
    private String content;
    //父评论ID
    private Long parentId;
    //评论时间
    private Date createTime;
    //用户id
    private Long commentUserId;
}
