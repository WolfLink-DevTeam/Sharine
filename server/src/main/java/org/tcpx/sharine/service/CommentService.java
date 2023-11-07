package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.dto.CommentDTO;
import org.tcpx.sharine.entity.Comment;
import org.tcpx.sharine.repository.CommentRepository;
import org.tcpx.sharine.vo.CommentVO;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository,UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public List<CommentVO> getComments(Long videoId) {
        return buildCommentVOs(commentRepository.findAllByVideoId(videoId));
    }

    public void addComment(CommentDTO commentDTO) {
        Long userId = userService.getSessionUserId();
        Comment comment = Comment.of(commentDTO);
        comment.setUserId(userId);
        commentRepository.save(comment);
    }
    public List<CommentVO> buildCommentVOs(List<Comment> comments) {
        return comments.stream().map(this::buildCommentVO).toList();
    }
    public CommentVO buildCommentVO(Comment comment) {
        CommentVO commentVO = CommentVO.of(comment);
        commentVO.setAuthor(userService.findUserSimpleInfo(comment.getUserId()));
        return commentVO;
    }
}
