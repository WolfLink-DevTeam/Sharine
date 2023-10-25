package org.tcpx.sharine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tcpx.sharine.enums.StatusCodeEnum;

/**
 * 业务异常
 *
 * @author bin
 * @date 2021/07/27
 */
@Getter
@AllArgsConstructor
public class WarnException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code = StatusCodeEnum.FAIL.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public WarnException(String message) {
        this.message = message;
    }

    public WarnException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }
}
