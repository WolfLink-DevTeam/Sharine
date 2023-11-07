package org.tcpx.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.UserPass;
import org.tcpx.sharine.service.UserService;
import org.tcpx.sharine.utils.IpUtils;
import org.tcpx.sharine.vo.UserLoginVO;
import org.tcpx.sharine.vo.UserSimpleVO;

/**
 * 用户信息控制器
 * 包含以下接口：
 * 登录相关接口 用户信息查询接口 用户动作接口 用户作品列表查询 用户喜欢视频列表查询 用户收藏视频列表查询
 */
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController extends BaseController {

    final UserService userService;

    /**
     * 用户登录接口
     *
     * @param userPass 登录通行证信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Object login(@RequestBody UserPass userPass) {
        UserSimpleVO userSimpleVO = userService.login(userPass);
        return ok(new UserLoginVO(userSimpleVO,StpUtil.getTokenValue()));
    }

    /**
     * 用户注册接口
     *
     * @param userPass 登录通行证信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Object register(HttpServletRequest request, @RequestBody UserPass userPass) {
        return ok(userService.register(IpUtils.getIpAddress(request),userPass));
    }

    /**
     * 用户修改密码接口
     *
     * @param userPass 新的登录通行证信息
     * @return 密码修改结果
     */
    @PostMapping("/changePassword")
    public Object changePassword(@RequestBody UserPass userPass) {
        return ok(userService.changePassword(userPass));
    }

    /**
     * 用户请求验证码接口
     *
     * @param userPass 登录通行证信息(可能还未注册)
     * @return 请求结果
     */
    @PostMapping("/sendCode")
    public Object requestForCode(HttpServletRequest request, @RequestBody UserPass userPass) {
        userService.requestForCode(IpUtils.getIpAddress(request),userPass);
        return ok();
    }

    /**
     * 查询指定用户详细信息
     *
     * @param userId 用户ID
     * @return 用户详细信息
     */
    @GetMapping("/{userId}")
    public Object findUserDetailInfo(@PathVariable Long userId) {
        return ok(userService.findUserDetailInfo(userId));
    }
}
