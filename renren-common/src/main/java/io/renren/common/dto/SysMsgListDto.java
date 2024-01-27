package io.renren.common.dto;

import lombok.Data;

@Data
public class SysMsgListDto {
    private Integer page;
    private Long userId;
    private Integer type;
}
