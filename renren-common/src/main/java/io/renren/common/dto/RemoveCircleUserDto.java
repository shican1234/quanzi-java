package io.renren.common.dto;

import lombok.Data;

@Data
public class RemoveCircleUserDto {
    private Long userId;
    private Long circleId;
}
