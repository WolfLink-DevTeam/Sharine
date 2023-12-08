package org.wolflink.sharine.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Video;
import org.wolflink.sharine.enums.MicroServiceEnum;

import java.util.List;

@FeignClient(MicroServiceEnum.VIDEO_SERVICE)
public interface VideoServiceClient {
    @PostMapping("/verify")
    ResultPack verifyVideo(
            @RequestBody Video video,
            @RequestParam("fileKey") String fileKey,
            @RequestParam("hash") String hash,
            @RequestParam("categoryId") Long categoryId
    );

    @GetMapping("/{videoId}")
    ResultPack getVideo(@PathVariable("videoId") Long videoId);

    @GetMapping
    ResultPack getVideos(@ModelAttribute List<Long> videoIds);

    @GetMapping("/page/{current}/{size}")
    ResultPack findVideos(@PathVariable("current") Integer current, @PathVariable("size") Integer size);

    @GetMapping("/upload/{userId}")
    ResultPack getUserUploadVideos(@PathVariable("userId") Long userId);
}
