package org.wolflink.sharine.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wolflink.sharine.api.enums.StatusCodeEnum;

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
    private Integer code;


    public WarnException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public WarnException(StatusCodeEnum statusCodeEnum) {
        super(statusCodeEnum.getDesc());
        this.code = statusCodeEnum.getCode();
    }
}
