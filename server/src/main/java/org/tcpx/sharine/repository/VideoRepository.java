package org.tcpx.sharine.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.Comment;
import org.tcpx.sharine.entity.Video;
@CacheConfig(cacheNames = DatabaseConst.VIDEO)
public interface VideoRepository extends JpaRepository<Video, Long> {
    @CacheEvict
    void delete(@NotNull Video entity);
}
