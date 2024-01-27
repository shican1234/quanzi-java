package io.renren.common.modules.post.entity;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.renren.common.dto.AddHongBaoDTO;
import io.renren.common.dto.AddPostDTO;
import io.renren.common.dto.AddVoteDTO;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.utils.DateUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 帖子表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2023-11-29
 */
@Data
@TableName("tb_post")
public class PostEntity {

    /**
     * 帖子ID
     */
	private Long id;
    /**
     * 帖子文字内容
     */
	private String content;
    /**
     * 文件地址,json格式
     */
	private String fileUrls;
    /**
     * 发帖的人的ID
     */
	private Long userId;
    /**
     * 圈子ID
     */
	private Long circleId;
    /**
     * 状态(0审核中1审核通过1拒绝)
     */
	private Integer status;
    /**
     * 类型(0公开帖子1圈内帖子)
     */
	private Integer type;
    /**
     * 发帖时间
     */
	private Date createTime;
    /**
     * 文件类型(0图片1视频)
     */
	private Integer fileType;
    /**
     * 话题ID
     */
	private Long discussId;
    /**
     * 标题
     */
	private String title;
    /**
     * 封面图(多张图片取第一个,视频就是第一帧)
     */
	private String image;

    /**
     * 点赞的数量
     */
    private Long dzCount;
    /**
     * 阅读量
     */
    private Long readNumer;

    @TableField(exist = false)
    private String nickName;
    @TableField(exist = false)
    private String avatar;
    @TableField(exist = false)
    private int isDz;


    @TableField(exist = false)
    private String circleName;
    /**
     * ip
     */
    private String ip;
    /**
     * ip位置
     */
    private String ipLocate;

    private Integer postClass;




    /**
     * 发帖人的角色
     */
    @TableField(exist = false)
    private int postRole;
    @TableField(exist = false)
    private Date vipDate;


    public PostEntity(UserEntity user, AddVoteDTO dto){
        this.userId = user.getId();
        this.content = dto.getContent();
        this.fileUrls = JSONObject.toJSONString(new ArrayList<String>());
        this.circleId = dto.getCircleId();
        this.status = 0;
        this.type = dto.getType();
        this.createTime = new Date();
        this.title = dto.getTitle();
        this.dzCount = 0l;
        this.readNumer = 0l;
        this.postClass = 2;
    }
    public PostEntity(){}
    public PostEntity(UserEntity user, AddPostDTO dto ,int fileType){
        this.userId = user.getId();
        this.content = dto.getContent();
        this.fileUrls = JSONObject.toJSONString(dto.getFileUrls());
        this.circleId = dto.getCircleId();
        this.status = 0;
        this.type = dto.getType();
        this.createTime = new Date();
        this.fileType = fileType;
        this.discussId = dto.getDiscussId();
        this.title = dto.getTitle();
        this.image = fileType == 0 ? dto.getFileUrls().get(0) : dto.getFileUrls().get(0)+"?spm=qipa250&x-oss-process=video/snapshot,t_7000,f_jpg,m_fast,ar_auto";
        this.dzCount = 0l;
        this.readNumer = 0l;
        this.postClass = 0;
    }
}