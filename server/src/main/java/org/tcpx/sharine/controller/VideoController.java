package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tcpx.sharine.dto.UserPass;
import org.tcpx.sharine.service.VideoService;

@RestController("/video")
public class VideoController extends BaseController {
    final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/verify/{fileName}/{md5}")
    public Object verifyVideo(@RequestBody UserPass userPass, @PathVariable String fileName, @PathVariable String md5) {
        // TODO 验证用户 Token



        videoService.verifyAndSaveVideo(userPass.getAccount(),fileName,md5);
        return ok();
    }
}
