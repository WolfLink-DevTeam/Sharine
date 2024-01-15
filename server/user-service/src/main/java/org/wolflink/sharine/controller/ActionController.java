package org.wolflink.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.lib.TryOptional;
import org.wolflink.sharine.service.UserService;
import org.wolflink.sharine.action.IpAction;

/**
 * 用户信息控制器
 * 包含以下接口：
 * 登录相关接口 用户信息查询接口 用户动作接口 用户作品列表查询 用户喜欢视频列表查询 用户收藏视频列表查询
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user-actions")
public class ActionController extends BaseController {

    private final UserService userService;
    private final IpAction ipAction;

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public Object login(@RequestParam String email, @RequestParam String password) {
        // 已经登录了，不处理
        if(StpUtil.isLogin()) return ok();
        return ok(userService.login(email,password));
    }

    /**
     * 用户注册接口
     */
    @PostMapping("/register")
    public Object register(HttpServletRequest request, @RequestParam String email, @RequestParam String encryptPassword, @RequestParam String emailCode) {
        userService.register(ipAction.getIpAddress(request), email,encryptPassword, emailCode);
        return ok();
    }

    /**
     * 用户修改密码接口
     */
    @PostMapping("/changePassword")
    public Object changePassword(@RequestParam String encryptPassword, @RequestParam String verificationCode) {
        // 连接是否已登录
        Long userId = TryOptional.tryWith(StpUtil::getLoginIdAsLong)
                .orElseThrow(()->new WarnException(StatusCodeEnum.NOT_LOGIN));
        userService.changePassword(userId,encryptPassword,verificationCode);
        return ok();
    }

    /**
     * 用户请求验证码接口
     *
     * @return 请求结果
     */
    @PostMapping("/sendCode")
    public Object requestForCode(HttpServletRequest request, @RequestParam String account) {
        userService.requestForCode(ipAction.getIpAddress(request),account);
        return ok();
    }

    /**
     * 查询指定用户详细信息
     *
     * @param userId 用户ID
     * @return 用户详细信息
     */
//    @GetMapping("/{userId}")
//    public Object findUserDetailInfo(@PathVariable Long userId) {
//        return ok(userService.findUserDetailInfo(userId));
//    }
}
