package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
