package org.wolflink.sharine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wolflink.sharine.enums.StatusCodeEnum;

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


    public ErrorException(String message) {
        super(message);
    }

    public ErrorException(StatusCodeEnum statusCodeEnum) {
        super(statusCodeEnum.getDesc());
        this.code = statusCodeEnum.getCode();
    }
}
