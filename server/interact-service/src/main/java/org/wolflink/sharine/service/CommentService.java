package org.wolflink.sharine.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wolflink.sharine.action.ObjectParseAction;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.repository.CommentRepository;
import org.wolflink.sharine.vo.CommentVO;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ObjectParseAction objectParseAction;

    public List<CommentVO> getComments(Long videoId) {
        return buildCommentVOs(commentRepository.findAllByVideoId(videoId));
    }

    public void addComment(CommentDTO commentDTO) {
        Comment comment = objectParseAction.parse(commentDTO);
        commentRepository.save(comment);
    }
    public List<CommentVO> buildCommentVOs(List<Comment> comments) {
        return comments.stream().map(this::buildCommentVO).toList();
    }
    public CommentVO buildCommentVO(Comment comment) {
        CommentVO commentVO = objectParseAction.parse(comment);
        commentVO.setAuthorId(comment.getUserId());
        return commentVO;
    }
}
