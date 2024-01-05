package org.wolflink.sharine.controller;

import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.service.UserService;

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
    Object post(
            @RequestParam Long id,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String avatar,
            @RequestParam(required = false) String content
    ) {
        userService.updateUser(id,nickname,avatar,content);
        return ok();
    }
}
