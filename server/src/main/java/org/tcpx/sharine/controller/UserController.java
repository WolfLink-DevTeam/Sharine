package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.UserPass;
import org.tcpx.sharine.service.UserService;

@RestController("/user")
public class UserController extends BaseController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录接口
     *
     * @param userPass 登录通行证信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Object login(@RequestBody UserPass userPass) {
        return ok(userService.login(userPass));
    }

    /**
     * 用户注册接口
     *
     * @param userPass 登录通行证信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Object register(@RequestBody UserPass userPass) {
        return ok(userService.register(userPass));
    }

    /**
     * 用户修改密码接口
     *
     * @param userPass 登录通行证信息
     * @return 密码修改结果
     */
    @PostMapping("/changePassword")
    public Object changePassword(@RequestBody UserPass userPass) {
        return ok(userService.changePassword(userPass));
    }

    /**
     * 用户请求验证码接口
     *
     * @param userPass 登录通行证信息
     * @return 请求结果
     */
    @PostMapping("/requestForCode")
    public Object requestForCode(@RequestBody UserPass userPass) {
        userService.requestForCode(userPass);
        return ok();
    }

    /**
     * 查询指定用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("{userId}")
    public Object findUserInfo(@PathVariable Long userId) {
        return ok(userService.findUserInfo(userId));
    }
}
