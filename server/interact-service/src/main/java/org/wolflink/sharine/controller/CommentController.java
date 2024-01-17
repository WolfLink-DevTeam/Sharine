package org.wolflink.sharine.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController {

    @Resource
    private CommentService commentService;
    @Resource
    private SessionAction sessionAction;
    @PostMapping
    Object post(@RequestBody Comment comment) {
        Long userId = sessionAction.getSessionUserId();
        if(!userId.equals(comment.getUserId())) throw new WarnException(StatusCodeEnum.UNAUTHORIZED);
        commentService.addComment(comment);
        return ok();
    }
    @GetMapping
    public Object getVideoComments(@RequestParam Long videoId) {
        return ok(commentService.getComments(videoId));
    }

    @DeleteMapping
    ResultPack delVideoComments(@RequestParam Long videoId) {
        commentService.delComments(videoId);
        return ok();
    }

}
