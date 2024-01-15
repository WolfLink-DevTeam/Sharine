package org.wolflink.sharine.controller;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.enums.StatusCodeEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseController {
    @ExceptionHandler(Exception.class)
    protected final ResultPack exception(Exception ex) {
        ex.printStackTrace();
        return error("程序内部错误: " + ex.getMessage());
    }
    protected final ResultPack error(StatusCodeEnum statusCodeEnum) {
        return createResult(statusCodeEnum.getCode(),statusCodeEnum.getDesc(),null);
    }
    protected final ResultPack error(String msg) {
        return createResult(StatusCodeEnum.SYSTEM_ERROR.getCode(), msg, null);
    }
    protected final ResultPack error(Integer code, String msg) {
        return createResult(code, msg, null);
    }

    protected final ResultPack warn(StatusCodeEnum statusCodeEnum) {
        return createResult(statusCodeEnum.getCode(), statusCodeEnum.getDesc(),null);
    }
    protected final ResultPack warn(String msg) {
        return createResult(StatusCodeEnum.FAIL.getCode(), msg, null);
    }

    protected final ResultPack warn(Integer code, String msg) {
        return createResult(code, msg, null);
    }

    protected final ResultPack ok(Object data) {
        return createResult(StatusCodeEnum.SUCCESS.getCode(), null, data);
    }

    protected final ResultPack ok() {
        return createResult(StatusCodeEnum.SUCCESS.getCode(), null, null);
    }

    protected final ResultPack todo() { return createResult(StatusCodeEnum.TODO.getCode(),StatusCodeEnum.TODO.getDesc(),null); }
    private ResultPack createResult(int code, String msg, Object data) {
        ResultPack pack = new ResultPack();
        pack.setCode(code);
        pack.setMsg(msg);
        pack.setData(data);
        return pack;
    }

    @InitBinder
    protected void dateBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(format, true);
        binder.registerCustomEditor(Date.class, editor);
    }

}
