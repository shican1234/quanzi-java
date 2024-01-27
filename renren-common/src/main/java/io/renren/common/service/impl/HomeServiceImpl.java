/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.common.dao.HomeDao;
import io.renren.common.dto.CircleCountDto;
import io.renren.common.dto.PayCountDto;
import io.renren.common.dto.PostCountDto;
import io.renren.common.dto.UserCountDto;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.page.PageData;
import io.renren.common.service.HomeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class HomeServiceImpl extends BaseServiceImpl<HomeDao, UserEntity> implements HomeService {


    @Override
    public UserCountDto queryUser() {
        return baseDao.queryUser();
    }

    @Override
    public PayCountDto queryPay() {
        return baseDao.queryPay();
    }

    @Override
    public CircleCountDto queryCircle() {
        return baseDao.queryCircle();
    }

    @Override
    public PostCountDto queryPost() {
        return baseDao.queryPost();
    }
}
