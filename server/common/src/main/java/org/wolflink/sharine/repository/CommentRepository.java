package org.wolflink.sharine.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.entity.Comment;

import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.COMMENT)
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @CacheEvict
    void delete(@NotNull Comment entity);

    List<Comment> findAllByVideoId(Long videoId);
}
