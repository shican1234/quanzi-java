package io.renren.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayCountDto {
    private BigDecimal todayPaySum;
    private BigDecimal thisWekPaySum;
    private BigDecimal thisMonPaySum;
    private BigDecimal allPaySum;
}
