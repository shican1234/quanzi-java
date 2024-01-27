package io.renren.common.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostCountDto {
    private BigDecimal todayPost;
    private BigDecimal thisWekPost;
    private BigDecimal thisMonPost;
    private BigDecimal allPost;
}
