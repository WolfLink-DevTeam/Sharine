package org.tcpx.sharine.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VideoTypeEnum {
    ORIGINAL(1, "原创"),

    Reprint(2, "转载");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

}
