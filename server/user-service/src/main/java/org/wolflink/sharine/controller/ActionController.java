package org.wolflink.sharine.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.service.UserService;
import org.wolflink.sharine.action.IpAction;

/**
 * 用户信息控制器
 * 包含以下接口：
 * 登录相关接口 用户信息查询接口 用户动作接口 用户作品列表查询 用户喜欢视频列表查询 用户收藏视频列表查询
 */
@RestController
@AllArgsConstructor
@RequestMapping("/actions")
public class ActionController extends BaseController {

    private final UserService userService;
    private final IpAction ipAction;

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public ResultPack login(@RequestParam String account, @RequestParam String password) {
        userService.login(account,password);
//        return ok(new UserLoginVO(userSimpleVO,StpUtil.getTokenValue()));
        return ok();
    }

    /**
     * 用户注册接口
     */
    @PostMapping("/register")
    public ResultPack register(HttpServletRequest request, @RequestParam String account,@RequestParam String password,@RequestParam String verificationCode) {
        userService.register(ipAction.getIpAddress(request),account,password,verificationCode);
        return ok();
    }

    /**
     * 用户修改密码接口
     */
    @PostMapping("/changePassword")
    public ResultPack changePassword(@RequestBody String account,@RequestBody String password,@RequestBody String verificationCode) {
        userService.changePassword(account,password,verificationCode);
        return ok();
    }

    /**
     * 用户请求验证码接口
     *
     * @return 请求结果
     */
    @PostMapping("/sendCode")
    public ResultPack requestForCode(HttpServletRequest request, @RequestParam String account) {
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
