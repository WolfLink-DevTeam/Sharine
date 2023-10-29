package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.dto.UploadVideoDTO;
import org.tcpx.sharine.service.VideoService;

@RestController("/"+ DatabaseConst.VIDEO)
public class VideoController extends BaseController {
    final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/verify")
    public Object verifyVideo(@RequestBody UploadVideoDTO uploadVideoDTO) {
        videoService.verifyAndSaveVideo(uploadVideoDTO);
        return ok();
    }

    @GetMapping("/info/{videoId}")
    public Object getVideoInfo(@PathVariable Long videoId) {
        return ok(videoService.findVideoInfo(videoId));
    }

    @PostMapping("/played/{userId}/{videoId}")
    public Object hasPlayedVideo(@PathVariable Long userId, @PathVariable Long videoId) {
        // TODO 视频播放统计
        return todo();
    }
}
