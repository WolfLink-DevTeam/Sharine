package org.tcpx.sharine.controller;


import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.vo.PageVO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class BaseController {
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
//    跟子类接口冲突，注释了
//    @GetMapping("hello")
//    public Object hello() {
//        return ok("Hello!");
//    }

}
