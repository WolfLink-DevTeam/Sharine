package org.wolflink.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Video;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.lib.TryOptional;
import org.wolflink.sharine.rpc.IVideoService;

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

    private final IVideoService videoService;

    @GetMapping
    public ResultPack getVideo(
            @RequestParam(required = false) Long videoId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
            ) {
        if(videoId != null) {
            return ok(videoService.findVideo(videoId));
        }
        if(userId != null) {
            return ok(videoService.findVideosByUserId(userId));
        }
        if(page != null && size != null) {
            return ok(videoService.findVideos(page, size));
        }
        return warn(StatusCodeEnum.FAILED_PRECONDITION);
    }
    @DeleteMapping
    public Object delVideo(@RequestParam Long videoId) {
        StpUtil.checkLogin();
        Long loginId = TryOptional.tryWith(StpUtil::getLoginIdAsLong).orElseThrow();
        Video video = videoService.findVideo(videoId);
        // 试图删除别人的视频
        if(!video.getUserId().equals(loginId)) throw new WarnException(StatusCodeEnum.UNAUTHORIZED);
        // 删除视频
        videoService.deleteVideo(videoId);
        return ok();
    }
    @PutMapping
    public Object putVideo(
            @RequestParam Long videoId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String coverUrl
    ) {
        StpUtil.checkLogin();
        Long loginId = TryOptional.tryWith(StpUtil::getLoginIdAsLong).orElseThrow();
        Video video = videoService.findVideo(videoId);
        // 试图修改别人的视频
        if(!video.getUserId().equals(loginId)) throw new WarnException(StatusCodeEnum.UNAUTHORIZED);
        videoService.updateVideo(videoId,title,content,coverUrl);
        return ok();
    }

    /**
     * TODO 获取用户订阅视频列表(不可查看他人订阅频道)
     * @return 订阅视频信息列表
     */
//    @GetMapping("/subscribe")
//    public Object getUserSubscribeVideos() {
//        // 用户自己的订阅频道列表
//        return ok(videoService.getSubscribeVideos(userService.getSessionUserId()));
//    }
}
