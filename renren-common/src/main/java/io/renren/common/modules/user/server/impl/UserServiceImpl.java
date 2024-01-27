/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.modules.user.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.dto.PageDTO;
import io.renren.common.dto.SearchDTO;
import io.renren.common.modules.token.entity.TokenEntity;
import io.renren.common.modules.token.service.TokenService;
import io.renren.common.modules.user.dao.UserDao;
import io.renren.common.modules.user.dto.UserDTO;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.modules.user.server.UserService;
import io.renren.common.dto.LoginDTO;
import io.renren.common.exception.ErrorCode;
import io.renren.common.exception.RenException;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.validator.AssertUtils;
import io.renren.common.vo.detailsNumberVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends CrudServiceImpl<UserDao, UserEntity, UserDTO> implements UserService {
	@Autowired
	private TokenService tokenService;

	@Override
	public UserEntity getByMobile(String mobile) {
		return baseDao.getUserByMobile(mobile);
	}

	@Override
	public UserEntity getUserByUserId(Long userId) {
		return baseDao.getUserByUserId(userId);
	}
	@Override
	public UserEntity getByEmail(String email) {
		return baseDao.getByEmail(email);
	}
	@Override
	public UserEntity login(LoginDTO dto) {

		UserEntity user = getByEmail(dto.getUserName());
		if(user == null){
			user = getByMobile(dto.getUserName());
		}
		AssertUtils.isNull(user, ErrorCode.ACCOUNT_PASSWORD_ERROR);
		//密码错误
		if(!user.getPassWord().equals(DigestUtils.sha256Hex(dto.getPassWord()))){
			throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
		}

		//获取登录token
		TokenEntity tokenEntity = tokenService.createToken(user.getId());

		Map<String, Object> map = new HashMap<>(2);
		map.put("token", tokenEntity.getToken());
		map.put("expire", tokenEntity.getExpireDate().getTime() - System.currentTimeMillis());

		user.setToken(tokenEntity.getToken());

		return user;
	}
	@Override
	public UserEntity getUserByUnionId(String unionId) {
		return baseDao.getUserByUnionId(unionId);
	}



	@Override
	public String creatToken(UserEntity user) {
		TokenEntity tokenEntity = tokenService.createToken(user.getId());
		return tokenEntity.getToken();
	}

	@Override
	public void updateUserById(Map<String, Object> map) {
		baseDao.updateUserById(map);
	}

	@Override
	public List<detailsNumberVo> getDetailsNumber(Long userId) {
		return baseDao.getDetailsNumber(userId);
	}

	@Override
	public List<UserEntity> getUserByPid(Long userId) {
		return baseDao.getUserByPid(userId);
	}

	@Override
	public List<UserEntity> querySubordinateList(Map<String, Object> map) {
		return baseDao.querySubordinateList(map);
	}

	@Override
	public UserEntity queryaccountnumber(Map<String, Object> map) {

		UserEntity queryaccountnumber = baseDao.queryaccountnumber(map);

		return queryaccountnumber;
	}

	@Override
	public List<UserEntity> hotBlogger(PageDTO pageDTO) {
		return baseDao.hotBlogger(pageDTO);
	}

	@Override
	public List<UserEntity> searchUser(SearchDTO searchDTO) {
		return baseDao.searchUser(searchDTO);
	}

	@Override
	public QueryWrapper<UserEntity> getWrapper(Map<String, Object> params){
		String id = (String)params.get("id");
		String nickName = (String)params.get("nickName");
		String mobile = (String)params.get("mobile");
		String pid = (String)params.get("pid");
		String email = (String)params.get("email");

		QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
		wrapper.eq(StringUtils.isNotBlank(id), "id", id);
		wrapper.eq(StringUtils.isNotBlank(nickName), "nick_name", nickName);
		wrapper.eq(StringUtils.isNotBlank(mobile), "mobile", mobile);
		wrapper.eq(StringUtils.isNotBlank(email), "email", email);
		wrapper.eq(StringUtils.isNotBlank(pid), "pid", pid);

		return wrapper;
	}
}