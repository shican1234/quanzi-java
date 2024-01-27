package io.renren.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CircleCountDto {
    private BigDecimal todayCircle;
    private BigDecimal thisWekCircle;
    private BigDecimal thisMonCircle;
    private BigDecimal allCircle;
}
