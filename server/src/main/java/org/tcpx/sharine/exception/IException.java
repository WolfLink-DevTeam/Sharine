package org.tcpx.sharine.exception;

public interface IException {
    /**
     * 错误码
     */
    Integer getCode();

    /**
     * 错误信息
     */
    String getMessage();
}
