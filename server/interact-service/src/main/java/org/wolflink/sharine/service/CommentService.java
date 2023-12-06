package org.wolflink.sharine.service;

import org.springframework.stereotype.Service;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.repository.CommentRepository;
import org.wolflink.sharine.vo.CommentVO;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentVO> getComments(Long videoId) {
        return buildCommentVOs(commentRepository.findAllByVideoId(videoId));
    }

    public void addComment(CommentDTO commentDTO) {
        Comment comment = Comment.of(commentDTO);
        commentRepository.save(comment);
    }
    public List<CommentVO> buildCommentVOs(List<Comment> comments) {
        return comments.stream().map(this::buildCommentVO).toList();
    }
    public CommentVO buildCommentVO(Comment comment) {
        CommentVO commentVO = CommentVO.of(comment);
        commentVO.setAuthorId(comment.getUserId());
        return commentVO;
    }
}
