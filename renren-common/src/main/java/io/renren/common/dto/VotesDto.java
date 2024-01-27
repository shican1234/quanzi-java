package io.renren.common.dto;

import lombok.Data;

@Data
public class VotesDto {
    private String content;
    private Long id;
    private Long postId;
}
