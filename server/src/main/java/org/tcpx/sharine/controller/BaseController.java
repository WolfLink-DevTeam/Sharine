package org.tcpx.sharine.controller;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.IException;
import org.tcpx.sharine.vo.PageVO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class BaseController {
    @ExceptionHandler(Exception.class)
    protected final Object exception(Exception ex) {
        return error("程序内部错误: " + ex.getMessage());
    }

    protected final Object error(String msg) {
        return createResult(StatusCodeEnum.SYSTEM_ERROR.getCode(), msg, null);
    }

    protected final Object error(Integer code, String msg) {
        return createResult(code, msg, null);
    }

    protected final Object warn(String msg) {
        return createResult(StatusCodeEnum.FAIL.getCode(), msg, null);
    }

    protected final Object warn(Integer code, String msg) {
        return createResult(code, msg, null);
    }

    protected final Object ok(Object data, Long total) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("list", data);
        map.put("total", total);
        return createResult(StatusCodeEnum.SUCCESS.getCode(), null, map);
    }

    /**
     * 执行代码块，并捕获异常，自动封装并反馈到前端
     * @param block     代码块
     * @return          响应结果
     */
    protected final Object respond(Supplier<Object> block) {
        Object result;
        try {
            result = ok(block.get());
        } catch (Exception e) {
            if(e instanceof IException iException) {
                result = createResult(iException.getCode(),iException.getMessage(),null);
            } else {
                result = createResult(StatusCodeEnum.SYSTEM_ERROR.getCode(),StatusCodeEnum.SYSTEM_ERROR.getDesc(),null);
            }
        }
        return result;
    }
    protected final Object respond(Runnable block) {
        Object result;
        try {
            block.run();
            result = ok();
        } catch (Exception e) {
            if(e instanceof IException iException) {
                result = createResult(iException.getCode(),iException.getMessage(),null);
            } else {
                result = createResult(StatusCodeEnum.SYSTEM_ERROR.getCode(),StatusCodeEnum.SYSTEM_ERROR.getDesc(),null);
            }
        }
        return result;
    }
    protected final Object ok(PageVO<?> data) {
        return ok(data.getList(), data.getTotal());
    }

    protected final Object ok(Object data) {
        return createResult(StatusCodeEnum.SUCCESS.getCode(), null, data);
    }

    protected final Object ok() {
        return createResult(StatusCodeEnum.SUCCESS.getCode(), null, null);
    }

    private Object createResult(int code, String msg, Object data) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    @InitBinder
    protected void dateBinder(WebDataBinder binder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(format, true);
        binder.registerCustomEditor(Date.class, editor);
    }

}
