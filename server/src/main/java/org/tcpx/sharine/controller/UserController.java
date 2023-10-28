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
        return respond(()->userService.login(userPass));
    }

    /**
     * 用户注册接口
     *
     * @param userPass 登录通行证信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Object register(@RequestBody UserPass userPass) {
        return respond(()->userService.register(userPass));
    }

    /**
     * 用户修改密码接口
     *
     * @param userPass 登录通行证信息
     * @return 密码修改结果
     */
    @PostMapping("/changePassword")
    public Object changePassword(@RequestBody UserPass userPass) {
        return respond(()->userService.changePassword(userPass));
    }

    /**
     * 用户请求验证码接口
     *
     * @param userPass 登录通行证信息
     * @return 请求结果
     */
    @PostMapping("/requestForCode")
    public Object requestForCode(@RequestBody UserPass userPass) {
        return respond(()->userService.requestForCode(userPass));
    }

    /**
     * 查询指定用户详细信息
     *
     * @param userId 用户ID
     * @return 用户详细信息
     */
    @GetMapping("/detail/{userId}")
    public Object findUserDetailInfo(@PathVariable Long userId) {
        return respond(()->userService.findUserDetailInfo(userId));
    }
    /**
     * 查询用户档案信息
     *
     * @param userId 用户ID
     * @return 用户档案信息
     */
    @GetMapping("/profile/{userId}")
    public Object findUserProfileInfo(@PathVariable Long userId) {
        return respond(()->userService.findUserProfileInfo(userId));
    }
}
