/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.modules.user.server;

import io.renren.common.dto.PageDTO;
import io.renren.common.dto.SearchDTO;
import io.renren.common.modules.user.dto.UserDTO;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.dto.LoginDTO;
import io.renren.common.service.BaseService;
import io.renren.common.service.CrudService;
import io.renren.common.vo.detailsNumberVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
public interface UserService extends CrudService<UserEntity, UserDTO> {

	UserEntity getByMobile(String mobile);

	UserEntity getUserByUserId(Long userId);

	/**
	 * 用户登录
	 * @param dto    登录表单
	 * @return        返回登录信息
	 */
	UserEntity login(LoginDTO dto);
	UserEntity getByEmail(String email);

	UserEntity getUserByUnionId(String unionId);


	String creatToken(UserEntity user);

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
