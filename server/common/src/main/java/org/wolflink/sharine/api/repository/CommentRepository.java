package org.wolflink.sharine.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wolflink.sharine.api.constants.DatabaseConst;
import org.wolflink.sharine.api.entity.Comment;

import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.COMMENT)
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @CacheEvict
    void delete(@NotNull Comment entity);

    List<Comment> findAllByVideoId(Long videoId);
}
