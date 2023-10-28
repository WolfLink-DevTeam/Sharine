package org.tcpx.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.VideoCategory;

import java.util.List;
@CacheConfig(cacheNames = DatabaseConst.VIDEO_CATEGORY)
public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {
    List<VideoCategory> findByCategoryId(Long categoryId, Pageable pageable);

    Long countByCategoryId(Long categoryId);
}
