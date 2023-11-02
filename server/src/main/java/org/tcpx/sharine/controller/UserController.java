package org.tcpx.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.dto.UserPass;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.service.BookmarkService;
import org.tcpx.sharine.service.FavoriteService;
import org.tcpx.sharine.service.UserService;
import org.tcpx.sharine.service.VideoService;
import org.tcpx.sharine.vo.UserSimpleVO;

import java.util.List;

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
    final FavoriteService favoriteService;
    final BookmarkService bookmarkService;
    final VideoService videoService;

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

    @GetMapping("/{userId}/favorites")
    public Object findUserFavorites(@PathVariable Long userId, ConditionDTO conditionDTO) {
        return ok(favoriteService.findUserFavoriteVideoIds(userId, conditionDTO));
    }

    @PostMapping("/{userId}/favorite/{videoId}")
    public Object favorite(@PathVariable Long userId, @PathVariable Long videoId) {
        favoriteService.favoriteVideo(userId, videoId);
        return ok();
    }

    @DeleteMapping("/{userId}/favorite/{videoId}")
    public Object undoFavorite(@PathVariable Long userId, @PathVariable Long videoId) {
        favoriteService.undoFavoriteVideo(userId, videoId);
        return ok();
    }

    @GetMapping("/{userId}/bookmarks")
    public Object findUserBookmarks(@PathVariable Long userId, ConditionDTO conditionDTO) {
        return ok(bookmarkService.findUserBookmarkVideoIds(userId, conditionDTO));
    }

    /**
     * 收藏视频
     * @param userId    用户ID
     * @param videoId   视频ID
     */
    @PostMapping("/{userId}/bookmark/{videoId}")
    public Object bookmark(@PathVariable Long userId, @PathVariable Long videoId) {
        bookmarkService.bookmarkVideo(userId, videoId);
        return ok();
    }

    /**
     * 取消收藏视频
     * @param userId    用户ID
     * @param videoId   视频ID
     */
    @DeleteMapping("/{userId}/bookmark/{videoId}")
    public Object undoBookmark(@PathVariable Long userId, @PathVariable Long videoId) {
        bookmarkService.undoBookmarkVideo(userId, videoId);
        return ok();
    }

    /**
     * TODO 要同时用到好几个 Service ，不知道该放哪个 Service 里面，我先写到这里吧
     */
    @GetMapping("/{userId}/videos/favorite")
    public Object getUserFavoriteVideos(@PathVariable Long userId, UserPass userPass) {
        // 未登录
        if (!StpUtil.isLogin()) {
            throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        }
        // 非本人禁止查看
        if (!userService.findUserSimpleInfo(userPass.getAccount()).getId().equals(userId)) {
            throw new WarnException(StatusCodeEnum.UNAUTHORIZED);
        }
        // 获取用户喜欢的视频ID列表
        List<Long> favoriteVideoIds = favoriteService.findUserFavoriteVideoIds(userId);
        // 转 VideoVO 列表
        return ok(videoService.findVideos(favoriteVideoIds));
    }

    /**
     * TODO 这个接口跟上面的差不多
     */
    @GetMapping("/{userId}/videos/bookmark")
    public Object getUserBookmarkVideos(@PathVariable Long userId, UserPass userPass) {
        // 未登录
        if (!StpUtil.isLogin()) {
            throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        }
        // 非本人禁止查看
        if (!userService.findUserSimpleInfo(userPass.getAccount()).getId().equals(userId)) {
            throw new WarnException(StatusCodeEnum.UNAUTHORIZED);
        }
        // 获取用户喜欢的视频ID列表
        List<Long> bookmarkVideoIds = bookmarkService.findUserBookmarkVideoIds(userId);
        // 转 VideoVO 列表
        return ok(videoService.findVideos(bookmarkVideoIds));
    }

    /**
     * 获取用户订阅视频列表
     * @param userPass  用户登录令牌
     * @return          订阅视频信息列表
     */
    @GetMapping("/{userId}/videos/subscribe")
    public Object getUserSubscribeVideos(UserPass userPass) {
        if(!StpUtil.isLogin()) throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        UserSimpleVO userSimpleVO = userService.findUserSimpleInfo(userPass.getAccount());
        return videoService.getSubscribeVideos(userSimpleVO.getId());
    }

    /**
     * 获取用户投稿视频列表
     * @param userId    用户ID
     * @return          投稿视频信息列表
     */
    @GetMapping("/{userId}/videos/upload")
    public Object getUserUploadVideos(@PathVariable Long userId) {
        return ok(videoService.findVideosByUserId(userId));
    }
}
