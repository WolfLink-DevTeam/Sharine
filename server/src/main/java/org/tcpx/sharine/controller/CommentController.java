package org.tcpx.sharine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.service.CommentService;

@RestController("/"+ DatabaseConst.COMMENT)
public class CommentController extends BaseController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get/{videoId}")
    public Object getComments(@PathVariable Long videoId) {
        return ok(commentService.getComments(videoId));
    }
}
