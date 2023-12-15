package org.wolflink.sharine.controller;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.QiniuAction;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.ResultPack;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.CommentRepository;
import org.wolflink.sharine.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController extends RestfulController<Comment,Long> {

    @Resource
    private CommentService commentService;

    public CommentController(CommentRepository repository) {
        super(repository);
    }

    @Override
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
