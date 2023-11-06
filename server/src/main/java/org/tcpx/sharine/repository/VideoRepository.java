package org.tcpx.sharine.repository;

import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.Comment;
import org.tcpx.sharine.entity.Video;

import java.util.Collection;
import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.VIDEO)
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Video v SET v.viewCount = v.viewCount + 1 WHERE v.id = :videoId")
    void incrementCount(Long videoId);
}
