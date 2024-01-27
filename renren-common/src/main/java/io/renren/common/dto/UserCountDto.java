package io.renren.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserCountDto {
    private BigDecimal todayUser;
    private BigDecimal thisWekUser;
    private BigDecimal thisMonUser;
    private BigDecimal allUser;
}
