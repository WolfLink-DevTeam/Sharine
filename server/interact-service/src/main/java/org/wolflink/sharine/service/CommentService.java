package org.wolflink.sharine.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.QiniuAction;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.enums.StatusCodeEnum;
import org.wolflink.sharine.exception.WarnException;
import org.wolflink.sharine.repository.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final QiniuAction qiniuAction;

    public List<Comment> getComments(Long videoId) {
        return commentRepository.findAllByVideoId(videoId);
    }

    public void addComment(Comment comment) {
        if(comment.getContent().isEmpty() || comment.getContent().isBlank()) throw new WarnException(StatusCodeEnum.FAILED_PRECONDITION);
        if(!qiniuAction.textSensor(comment.getContent())) throw new WarnException(StatusCodeEnum.JUDGE_FAILED);
        commentRepository.save(comment);
    }
    public void delComments(Long videoId) {
        commentRepository.deleteAllByVideoId(videoId);
    }
//    public List<Comment> buildCommentVOs(List<Comment> comments) {
//        return comments.stream().map(this::buildCommentVO).toList();
//    }
//    public CommentVO buildCommentVO(Comment comment) {
//        CommentVO commentVO = objectParseAction.parse(comment);
//        commentVO.setAuthorId(comment.getUserId());
//        return commentVO;
//    }
}
