package org.wolflink.sharine.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController {

    @Resource
    private CommentService commentService;

    @PostMapping
    ResultPack post(Comment comment) {
        commentService.addComment(comment);
        return ok();
    }
    @GetMapping("/byVideoId/{videoId}")
    public Object getVideoComments(@PathVariable Long videoId) {
        return commentService.getComments(videoId);
    }

    @DeleteMapping("/byVideoId/{videoId}")
    ResultPack delVideoComments(@PathVariable Long videoId) {
        commentService.delComments(videoId);
        return ok();
    }

}
