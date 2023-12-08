package org.wolflink.sharine.dto;

import lombok.Data;

@Data
public class ResultPack {
    Integer code;
    String msg;
    Object data;
}
