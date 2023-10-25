package org.tcpx.sharine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tcpx.sharine.enums.StatusCodeEnum;

/**
 * @author march
 * @since 2023/5/31 上午10:47
 */
@Getter
@AllArgsConstructor
public class ErrorException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code = StatusCodeEnum.SYSTEM_ERROR.getCode();

    /**
     * 错误信息
     */
    private final String message;

    public ErrorException(String message) {
        this.message = message;
    }

    public ErrorException(StatusCodeEnum statusCodeEnum) {
        this.code = statusCodeEnum.getCode();
        this.message = statusCodeEnum.getDesc();
    }
}
