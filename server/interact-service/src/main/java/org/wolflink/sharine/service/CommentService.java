package org.wolflink.sharine.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.repository.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getComments(Long videoId) {
        return commentRepository.findAllByVideoId(videoId);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
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
