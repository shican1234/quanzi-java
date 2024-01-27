/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.common.dto;

import lombok.Data;

/**
 * 注册表单
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class QueryCirclePostDTO {
   //分页
   private Integer page;
   //0查询全部  1查询精华 2查询圈主帖  3查询图片贴  4查询视频帖
   private Integer type;
   //圈子ID
   private Long id;
   //当前登入用户ID
   private Long userId;
   //圈主ID
   private Long cUid;
}
