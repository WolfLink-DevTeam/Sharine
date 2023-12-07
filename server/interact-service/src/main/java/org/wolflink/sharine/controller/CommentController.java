package org.wolflink.sharine.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wolflink.sharine.action.QiniuUtils;
import org.wolflink.sharine.action.SessionAction;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.service.CommentService;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentController extends BaseController {

    private final SessionAction sessionAction;
    private final QiniuUtils qiniuUtils;
    private final CommentService commentService;

    @PostMapping("/{videoId}")
    public Object addVideoComments(@RequestBody CommentDTO commentDTO) {
        User user = sessionAction.getSessionUser();
        if(commentDTO.getContent().isEmpty() || commentDTO.getContent().isBlank()) throw new WarnException(StatusCodeEnum.FAILED_PRECONDITION);
        if(!qiniuUtils.textSensor(commentDTO.getContent())) throw new WarnException(StatusCodeEnum.JUDGE_FAILED);
        commentService.addComment(commentDTO);
        return ok();
    }
    @GetMapping("/{videoId}")
    public Object getVideoComments(@PathVariable Long videoId) {
        return ok(commentService.getComments(videoId));
    }

}
