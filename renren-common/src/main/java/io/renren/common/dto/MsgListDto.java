package io.renren.common.dto;

import lombok.Data;

@Data
public class MsgListDto {
    private Integer page;
    private Long userId;
    private Long toUserId;

    private String msgSignA;
    private String msgSignB;
}
