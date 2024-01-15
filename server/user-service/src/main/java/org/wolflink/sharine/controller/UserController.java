package org.wolflink.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.lib.TryOptional;
import org.wolflink.sharine.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    UserService userService;
    @GetMapping
    Object get(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String emailStr
    ) {
        if(id != null) return ok(userService.findUser(id));
        if(emailStr != null) return ok(userService.findUser(emailStr));
        return error(StatusCodeEnum.FAILED_PRECONDITION);
    }
    @PutMapping
    Object put(
            @RequestParam Long id,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String avatar,
            @RequestParam(required = false) String content
    ) {
        // 连接是否已登录
        Long userId = TryOptional.tryWith(StpUtil::getLoginIdAsLong)
                .orElseThrow(()->new WarnException(StatusCodeEnum.NOT_LOGIN));
        // 操作的用户是否是自己
        if(!userId.equals(id)) return warn(StatusCodeEnum.UNAUTHORIZED);
        userService.updateUser(id,nickname,avatar,content,null);
        return ok();
    }
}
