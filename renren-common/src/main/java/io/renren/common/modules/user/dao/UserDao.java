/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.modules.user.dao;

import io.renren.common.dto.PageDTO;
import io.renren.common.dto.SearchDTO;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.dao.BaseDao;
import io.renren.common.vo.detailsNumberVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface UserDao extends BaseDao<UserEntity> {
    UserEntity getUserByMobile(String mobile);

    UserEntity getUserByUserId(Long userId);
    UserEntity getByEmail(String email);

    UserEntity getUserByUnionId(String unionId);

    /**
     * 根据用户ID修改用户数据
     *
     */
    void updateUserById(Map<String,Object> map);

    /**
     * 获取我的页面关注,粉丝等数据
     *
     */
    List<detailsNumberVo> getDetailsNumber(Long userId);

    /**
     * 根据用户ID查询用户所有下级
     *
     */
    List<UserEntity> getUserByPid(Long userId);

    /**
     * 根据用户ID查询用户所有下级
     *
     */
    List<UserEntity> querySubordinateList(Map<String,Object> map);

    /**
     * 查询账号是否已被绑定
     *
     */
    UserEntity queryaccountnumber(Map<String,Object> map);

    List<UserEntity> hotBlogger(PageDTO pageDTO);

    List<UserEntity> searchUser(SearchDTO searchDTO);
}
