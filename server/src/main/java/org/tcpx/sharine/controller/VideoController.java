package org.tcpx.sharine.controller;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tcpx.sharine.dto.CommentDTO;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.dto.UploadVideoDTO;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.enums.StatusCodeEnum;
import org.tcpx.sharine.exception.WarnException;
import org.tcpx.sharine.service.CommentService;
import org.tcpx.sharine.service.UserService;
import org.tcpx.sharine.service.VideoService;
import org.tcpx.sharine.service.ViewCountService;
import org.tcpx.sharine.utils.IpUtils;
import org.tcpx.sharine.utils.QiniuUtils;

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

    @PutMapping("/{videoId}/comments")
    public Object addVideoComments(@RequestBody CommentDTO commentDTO) {
        User user = userService.verifyUserPass(commentDTO.getUserPass());
        if(commentDTO.getContent().isEmpty() || commentDTO.getContent().isBlank()) throw new WarnException(StatusCodeEnum.FAILED_PRECONDITION);
        if(!qiniuUtils.textSensor(commentDTO.getContent())) throw new WarnException(StatusCodeEnum.JUDGE_FAILED);
        commentService.addComment(commentDTO);
        return ok();
    }
    @GetMapping("/{videoId}/addViewCount")
    public Object addViewCount(HttpServletRequest request, @PathVariable Long videoId) {
        String ip = IpUtils.getIpAddress(request);
        viewCountService.addViewCount(ip,videoId);
        return ok();
    }
    @GetMapping
    public Object findVideos(ConditionDTO conditionDTO) {
        return ok(videoService.findVideos(conditionDTO));
    }
}
