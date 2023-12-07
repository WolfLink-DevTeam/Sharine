package org.wolflink.sharine.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.dto.ConditionDTO;
import org.wolflink.sharine.dto.UploadVideoDTO;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.BookmarkRepository;
import org.wolflink.sharine.service.*;
import org.wolflink.sharine.action.IpUtils;
import org.wolflink.sharine.action.QiniuUtils;

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
    final QiniuUtils qiniuUtils;
    final ViewCountService viewCountService;
    final BookmarkRepository bookmarkRepository;
    final SessionAction sessionAction;

    @PostMapping("/verify")
    public Object verifyVideo(@RequestBody UploadVideoDTO uploadVideoDTO) {
        videoService.verifyAndSaveVideo(uploadVideoDTO);
        return ok();
    }

    @GetMapping("/{videoId}")
    public Object getVideoInfo(@PathVariable Long videoId) {
        return ok(videoService.findVideoInfo(videoId));
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
     * TODO 获取用户订阅视频列表(不可查看他人订阅频道)
     * @return          订阅视频信息列表
     */
//    @GetMapping("/subscribe")
//    public Object getUserSubscribeVideos() {
//        // 用户自己的订阅频道列表
//        return ok(videoService.getSubscribeVideos(userService.getSessionUserId()));
//    }

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
