package io.renren.common.dto;

import lombok.Data;

@Data
public class PassOrUnPassDto {
    private Long id;//好友请求消息ID
    private Integer type;//1通过2拒绝
}
