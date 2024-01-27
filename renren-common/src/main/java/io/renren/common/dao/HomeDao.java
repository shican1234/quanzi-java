/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.dto.CircleCountDto;
import io.renren.common.dto.PayCountDto;
import io.renren.common.dto.PostCountDto;
import io.renren.common.dto.UserCountDto;
import io.renren.common.modules.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface HomeDao extends BaseDao<UserEntity> {


    UserCountDto queryUser();

    PayCountDto queryPay();

    CircleCountDto queryCircle();

    PostCountDto queryPost();

}