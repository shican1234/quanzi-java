package io.renren.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CmsListVo {
    private long vod_id;
    private String vod_name;
    private int type_id;
    private String type_name;
    private String vod_en;
    private Date vod_time;
    private String vod_remarks;
    private String vod_play_from;
    private String vod_play_url;
}
