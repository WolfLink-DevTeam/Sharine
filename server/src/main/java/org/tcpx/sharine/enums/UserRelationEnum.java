package org.tcpx.sharine.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRelationEnum {
    NONE(0, "原创"),

    FOLLOW(1, "关注"),
    FOLLOWED(2, "被关注"),
    FRIEND(3, "朋友");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;
}
