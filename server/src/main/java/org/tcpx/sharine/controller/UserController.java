package org.tcpx.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.dto.UserLoginDTO;
import org.tcpx.sharine.dto.UserPass;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.service.BookmarkService;
import org.tcpx.sharine.service.FavoriteService;
import org.tcpx.sharine.service.UserService;
import org.tcpx.sharine.service.VideoService;
import org.tcpx.sharine.utils.IpUtils;
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
        UserSimpleVO userSimpleVO = userService.login(userPass);
        String token = StpUtil.getTokenValue();
        return ok(new UserLoginDTO(userSimpleVO,token));
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

//    使用 getUserFavoriteVideos 接口代替这个接口
//    @GetMapping("/{userId}/favorites")
//    public Object findUserFavorites(@PathVariable Long userId, ConditionDTO conditionDTO) {
//        return ok(favoriteService.findUserFavoriteVideoIds(userId, conditionDTO));
//    }

    @PostMapping("/favorite/{videoId}")
    public Object favorite(UserPass userPass, @PathVariable Long videoId) {
        if(!StpUtil.isLogin()) throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        User user = userService.verifyUserPass(userPass);
        favoriteService.favoriteVideo(user.getId(), videoId);
        return ok();
    }

    @DeleteMapping("/favorite/{videoId}")
    public Object undoFavorite(UserPass userPass, @PathVariable Long videoId) {
        if(!StpUtil.isLogin()) throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        User user = userService.verifyUserPass(userPass);
        favoriteService.undoFavoriteVideo(user.getId(), videoId);
        return ok();
    }
//    使用 getUserBookmarkVideos 接口代替这个接口
//    @GetMapping("/{userId}/bookmarks")
//    public Object findUserBookmarks(@PathVariable Long userId, ConditionDTO conditionDTO) {
//        return ok(bookmarkService.findUserBookmarkVideoIds(userId, conditionDTO));
//    }

    /**
     * 收藏视频
     * @param userPass  用户令牌
     * @param videoId   视频ID
     */
    @PostMapping("/bookmark/{videoId}")
    public Object bookmark(UserPass userPass, @PathVariable Long videoId) {
        if(!StpUtil.isLogin()) throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        User user = userService.verifyUserPass(userPass);
        bookmarkService.bookmarkVideo(user.getId(), videoId);
        return ok();
    }

    /**
     * 取消收藏视频
     * @param userPass  用户令牌
     * @param videoId   视频ID
     */
    @DeleteMapping("/bookmark/{videoId}")
    public Object undoBookmark(UserPass userPass, @PathVariable Long videoId) {
        if(!StpUtil.isLogin()) throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        User user = userService.verifyUserPass(userPass);
        bookmarkService.undoBookmarkVideo(user.getId(), videoId);
        return ok();
    }

    /**
     * TODO 要同时用到好几个 Service ，不知道该放哪个 Service 里面，我先写到这里吧
     * 只允许查询用户自己的视频列表数据
     */
    @PostMapping("/favorite/videos")
    public Object getUserFavoriteVideos(UserPass userPass) {
        // 当前 Session 是否登录
        if (!StpUtil.isLogin()) {
            throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        }
        // 用户通行证是否正确
        User user = userService.verifyUserPass(userPass);
        // 获取用户喜欢的视频ID列表
        List<Long> favoriteVideoIds = favoriteService.findUserFavoriteVideoIds(user.getId());
        // 转 VideoVO 列表
        return ok(videoService.findVideos(favoriteVideoIds));
    }

    /**
     * TODO 这个接口跟上面的差不多
     * 只允许查询用户自己的视频列表数据
     */
    @PostMapping("/bookmark/videos")
    public Object getUserBookmarkVideos(UserPass userPass) {
        // 当前 Session 是否登录
        if (!StpUtil.isLogin()) throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        // 用户通行证是否正确
        User user = userService.verifyUserPass(userPass);
        // 获取用户喜欢的视频ID列表
        List<Long> bookmarkVideoIds = bookmarkService.findUserBookmarkVideoIds(user.getId());
        // 转 VideoVO 列表
        return ok(videoService.findVideos(bookmarkVideoIds));
    }

    /**
     * 获取用户订阅视频列表(不可查看他人订阅频道)
     * @param userPass  用户登录令牌
     * @return          订阅视频信息列表
     */
    @PostMapping("/subscribe/videos")
    public Object getUserSubscribeVideos(UserPass userPass) {
        // 当前 Session 是否登录
        if(!StpUtil.isLogin()) throw new WarnException(StatusCodeEnum.NOT_LOGIN);
        // 用户通行证是否正确
        User user = userService.verifyUserPass(userPass);
        // 用户自己的订阅频道列表
        return videoService.getSubscribeVideos(user.getId());
    }

    /**
     * 获取用户投稿视频列表
     * @param userId    用户ID
     * @return          投稿视频信息列表
     */
    @GetMapping("/upload/videos/{userId}")
    public Object getUserUploadVideos(@PathVariable Long userId) {
        return ok(videoService.findVideosByUserId(userId));
    }
}
