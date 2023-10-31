package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.dto.UserPass;
import org.tcpx.sharine.service.BookmarkService;
import org.tcpx.sharine.service.FavoriteService;
import org.tcpx.sharine.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    final UserService userService;

    final FavoriteService favoriteService;

    final BookmarkService bookmarkService;

    public UserController(UserService userService, FavoriteService favoriteService, BookmarkService bookmarkService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.bookmarkService = bookmarkService;
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
    @PostMapping("/sendCode")
    public Object requestForCode(@RequestBody UserPass userPass) {
        userService.requestForCode(userPass);
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

    @GetMapping("/{userId}/favourites")
    public Object findUserFavourites(@PathVariable Long userId, ConditionDTO conditionDTO) {
        return ok(favoriteService.findUserFavourites(userId, conditionDTO));
    }
    @PostMapping("/{userId}/favourite/{videoId}")
    public Object favourite(@PathVariable Long userId, @PathVariable Long videoId) {
        favoriteService.favoriteVideo(userId, videoId);
        return ok();
    }

    @DeleteMapping("/{userId}/favourite/{videoId}")
    public Object unfavoured(@PathVariable Long userId, @PathVariable Long videoId) {
        favoriteService.undoFavoriteVideo(userId, videoId);
        return ok();
    }

    @GetMapping("/{userId}/bookmarks")
    public Object findUserBookmarks(@PathVariable Long userId, ConditionDTO conditionDTO) {
        return ok(bookmarkService.findUserBookmarks(userId, conditionDTO));
    }
    @PostMapping("/{userId}/bookmark/{videoId}")
    public Object bookmark(@PathVariable Long userId, @PathVariable Long videoId) {
        bookmarkService.bookmarkVideo(userId, videoId);
        return ok();
    }

    @DeleteMapping("/{userId}/bookmark/{videoId}")
    public Object unbookmark(@PathVariable Long userId, @PathVariable Long videoId) {
        bookmarkService.undoBookmarkVideo(userId, videoId);
        return ok();
    }
}
