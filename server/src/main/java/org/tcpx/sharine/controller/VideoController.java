package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.CommentDTO;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.dto.UploadVideoDTO;
import org.tcpx.sharine.service.CommentService;
import org.tcpx.sharine.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController extends BaseController {
    final VideoService videoService;

    final CommentService commentService;

    public VideoController(VideoService videoService, CommentService commentService) {
        this.videoService = videoService;
        this.commentService = commentService;
    }

    @PutMapping("/verify")
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

    @PutMapping("/{videoId}/comments")
    public Object addVideoComments(@PathVariable Long videoId, @RequestBody CommentDTO commentDTO) {
        commentDTO.setVideoId(videoId);

        commentService.add(commentDTO);
        return ok();
    }

    @GetMapping
    public Object findVideos(ConditionDTO conditionDTO) {
        return ok(videoService.findVideos(conditionDTO));
    }
}
