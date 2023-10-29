package org.tcpx.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.VideoCategoryRelation;

import java.util.List;
@CacheConfig(cacheNames = DatabaseConst.VIDEO_CATEGORY_RELATION)
public interface VideoCategoryRepository extends JpaRepository<VideoCategoryRelation, Long> {
    @Cacheable(unless = "#result==null||result.size()==0")
    List<VideoCategoryRelation> findByCategoryId(Long categoryId, Pageable pageable);
    @Cacheable(unless = "#result==null")
    Long countByCategoryId(Long categoryId);
}
