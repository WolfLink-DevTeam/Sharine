package org.wolflink.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Video;
import org.wolflink.sharine.rpc.IVideoService;

@RestController
@AllArgsConstructor
@RequestMapping("/video-actions")
public class ActionController extends BaseController {
    private final IVideoService videoService;
    @PostMapping("/signature")
    public ResultPack signature(
            @RequestParam String fileKey,
            @RequestParam String hash,
            @RequestParam Long categoryId,
            @RequestBody Video video
    ) {
        StpUtil.checkLogin();
        videoService.signature(video, fileKey, hash, categoryId);
        return ok();
    }
}
