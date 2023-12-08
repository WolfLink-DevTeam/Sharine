package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.QiniuAction;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.service.CommentService;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController extends BaseController {

    private final QiniuAction qiniuAction;
    private final CommentService commentService;

    @PostMapping("/{videoId}")
    public ResultPack addVideoComment(@RequestBody Comment comment) {
        if(comment.getContent().isEmpty() || comment.getContent().isBlank()) throw new WarnException(StatusCodeEnum.FAILED_PRECONDITION);
        if(!qiniuAction.textSensor(comment.getContent())) throw new WarnException(StatusCodeEnum.JUDGE_FAILED);
        commentService.addComment(comment);
        return ok();
    }
    @GetMapping("/{videoId}")
    public ResultPack getVideoComments(@PathVariable Long videoId) {
        return ok(commentService.getComments(videoId));
    }

}
