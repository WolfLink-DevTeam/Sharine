package org.wolflink.sharine.api.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wolflink.sharine.api.constants.DatabaseConst;
import org.wolflink.sharine.api.entity.VideoCategoryRelation;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = DatabaseConst.VIDEO_CATEGORY_RELATION)
public interface VideoCategoryRepository extends JpaRepository<VideoCategoryRelation, Long> {
    @Cacheable(unless = "#result==null||result.size()==0")
    List<VideoCategoryRelation> findByCategoryId(Long categoryId, Pageable pageable);
    Optional<VideoCategoryRelation> findByVideoId(Long videoId);
    @Cacheable(unless = "#result==null")
    Integer countByCategoryId(Long categoryId);
}
