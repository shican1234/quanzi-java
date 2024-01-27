package io.renren.common.vo;

import lombok.Data;

@Data
public class UnReadSysTemVo {
    private Long systemMsgCount;
    private Long commentMsgCount;
    private Long fabulousMsgCount;
    private Long friendMsgCount;
}
