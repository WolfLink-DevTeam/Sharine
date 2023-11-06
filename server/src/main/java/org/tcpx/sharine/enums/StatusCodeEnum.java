package org.tcpx.sharine.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeEnum {
    SUCCESS(0, "操作成功"),
    UNAUTHORIZED(1, "没有操作权限"),
    SYSTEM_ERROR(2, "系统异常"),
    FAIL(3, "操作失败"),
    FAILED_PRECONDITION(4, "参数格式不正确"),
    DATA_EXIST(5, "数据已存在"),
    DATA_NOT_EXIST(6, "数据不存在"),
    LOGIN_ERROR(7, "登陆错误"),
    PASSWORD_NOT_MATCHED(8, " 密码错误"),
    INDEX_OUT_OF_BOUND(9, "下标越界"),
    UNPACK_ERROR(10, "解压错误"),
    MKDIR_ERROR(11, "创建文件夹错误"),
    WRITE_ERROR(12, "写入文件错误"),
    JUDGE_ERROR(13, "评测时异常"),
    LANGUAGE_NOT_SUPPORT(14, "不支持该语言"),
    COMPILE_ERROR(15, "编辑错误"),
    RUNNING(16, "正在运行"),
    NOT_RUNNING(17, "不在运行时间"),
    NOT_LOGIN(18, "未登录"),
    VERIFY_FAILED(19,"验证失败"),
    TODO(20,"计划开发中，暂未实现"),
    JUDGE_FAILED(21,"信息审核不通过")
    ;

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;
}
