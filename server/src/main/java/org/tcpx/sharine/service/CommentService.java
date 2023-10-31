package org.tcpx.sharine.service;

import org.springframework.stereotype.Service;
import org.tcpx.sharine.dto.CommentDTO;
import org.tcpx.sharine.entity.Comment;
import org.tcpx.sharine.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(Long videoId) {
        return commentRepository.findAllByVideoId(videoId);
    }

    public void add(CommentDTO commentDTO) {
        Comment comment = Comment.of(commentDTO);

        commentRepository.save(comment);
    }
}
