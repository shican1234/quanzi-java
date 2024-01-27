/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.service;

import io.renren.common.dto.CircleCountDto;
import io.renren.common.dto.PayCountDto;
import io.renren.common.dto.PostCountDto;
import io.renren.common.dto.UserCountDto;
import io.renren.common.modules.user.entity.UserEntity;
import io.renren.common.page.PageData;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HomeService extends BaseService<UserEntity> {


    UserCountDto queryUser();

    PayCountDto queryPay();

    CircleCountDto queryCircle();


    PostCountDto queryPost();

}
