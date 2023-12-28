package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Video;
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

    @PostMapping("/verify")
    public ResultPack verifyVideo(
            @RequestBody Video video,
            @RequestParam String fileKey,
            @RequestParam String hash,
            @RequestParam Long categoryId
    ) {
        videoService.verifyAndSaveVideo(video, fileKey, hash, categoryId);
        return ok();
    }

    @GetMapping("/{videoId}")
    public ResultPack getVideo(@PathVariable Long videoId) {
        return ok(videoService.findVideo(videoId));
    }
    @GetMapping
    public ResultPack getVideos(@ModelAttribute List<Long> videoIds) {
        return ok(videoService.findVideos(videoIds));
    }

    @GetMapping("/page/{current}/{size}")
    public ResultPack findVideos(@PathVariable Integer current, @PathVariable Integer size) {
        return ok(videoService.findVideos(current, size));
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

    /**
     * 获取用户投稿视频ID列表
     *
     * @param userId 用户ID
     * @return 投稿视频信息列表
     */
    @GetMapping("/upload/{userId}")
    public ResultPack getUserUploadVideos(@PathVariable Long userId) {
        return ok(videoService.findVideosByUserId(userId));
    }
}
