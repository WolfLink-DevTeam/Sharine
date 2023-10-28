package org.tcpx.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.Comment;
@CacheConfig(cacheNames = DatabaseConst.COMMENT)
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
