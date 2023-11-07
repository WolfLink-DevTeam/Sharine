package org.tcpx.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.CommentDTO;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.dto.UploadVideoDTO;
import org.tcpx.sharine.dto.UserPass;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.service.*;
import org.tcpx.sharine.utils.IpUtils;
import org.tcpx.sharine.utils.QiniuUtils;

import java.util.List;

/**
 * 视频信息控制器
 * 包含以下接口：
 * 用户投稿视频验证 视频信息查询 视频搜索 获取视频评论 添加视频评论
 */
@RestController
@AllArgsConstructor
@RequestMapping("/videos")
public class VideoController extends BaseController {

    final VideoService videoService;
    final CommentService commentService;
    final UserService userService;
    final QiniuUtils qiniuUtils;
    final ViewCountService viewCountService;
    final FavoriteService favoriteService;
    final BookmarkService bookmarkService;

    @PostMapping("/verify")
    public Object verifyVideo(@RequestBody UploadVideoDTO uploadVideoDTO) {
        videoService.verifyAndSaveVideo(uploadVideoDTO);
        return ok();
    }

    @GetMapping("/{videoId}")
    public Object getVideoInfo(@PathVariable Long videoId) {
        return ok(videoService.findVideoInfo(videoId));
    }

    @GetMapping("/{videoId}/comments")
    public Object getVideoComments(@PathVariable Long videoId) {
        return ok(commentService.getComments(videoId));
    }

    @PostMapping("/{videoId}/comments")
    public Object addVideoComments(@RequestBody CommentDTO commentDTO) {
        User user = userService.getSessionUser();
        if(commentDTO.getContent().isEmpty() || commentDTO.getContent().isBlank()) throw new WarnException(StatusCodeEnum.FAILED_PRECONDITION);
        if(!qiniuUtils.textSensor(commentDTO.getContent())) throw new WarnException(StatusCodeEnum.JUDGE_FAILED);
        commentService.addComment(commentDTO);
        return ok();
    }
    @PostMapping("/{videoId}/addViewCount")
    public Object addViewCount(HttpServletRequest request, @PathVariable Long videoId) {
        String ip = IpUtils.getIpAddress(request);
        viewCountService.addViewCount(ip,videoId);
        return ok();
    }
    @GetMapping
    public Object findVideos(ConditionDTO conditionDTO) {
        return ok(videoService.findVideos(conditionDTO));
    }
    /**
     * 只允许查询用户自己的点赞视频列表数据
     */
    @GetMapping("/favorite")
    public Object getUserFavoriteVideos() {
        // 获取用户喜欢的视频ID列表
        List<Long> favoriteVideoIds = favoriteService.findUserFavoriteVideoIds(userService.getSessionUserId());
        // 转 VideoVO 列表
        return ok(videoService.findVideos(favoriteVideoIds));
    }

    /**
     * 只允许查询用户自己的收藏视频列表数据
     */
    @GetMapping("/bookmark")
    public Object getUserBookmarkVideos() {
        // 获取用户喜欢的视频ID列表
        List<Long> bookmarkVideoIds = bookmarkService.findUserBookmarkVideoIds(userService.getSessionUserId());
        // 转 VideoVO 列表
        return ok(videoService.findVideos(bookmarkVideoIds));
    }

    /**
     * 获取用户订阅视频列表(不可查看他人订阅频道)
     * @return          订阅视频信息列表
     */
    @GetMapping("/subscribe")
    public Object getUserSubscribeVideos() {
        // 用户自己的订阅频道列表
        return ok(videoService.getSubscribeVideos(userService.getSessionUserId()));
    }

    /**
     * 获取用户投稿视频列表
     * @param userId    用户ID
     * @return          投稿视频信息列表
     */
    @GetMapping("/upload/{userId}")
    public Object getUserUploadVideos(@PathVariable Long userId) {
        return ok(videoService.findVideoVOsByUserId(userId));
    }
}
