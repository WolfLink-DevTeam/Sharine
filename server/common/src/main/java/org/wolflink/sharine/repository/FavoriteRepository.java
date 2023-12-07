package org.wolflink.sharine.repository;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.entity.Favorite;

import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.FAVOURITE)
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
//    @Cacheable(unless = "#result==null")
    Integer countByUserId(Long userId);
//    @Cacheable(unless = "#result==null")
    Integer countByVideoId(Long videoId);
    @Modifying
    @Transactional
    void deleteByUserIdAndVideoId(Long userId, Long videoId);

    boolean existsByUserIdAndVideoId(Long userId, Long videoId);

    List<Favorite> findAllByUserId(Long userId, Pageable pageable);
    List<Favorite> findAllByUserId(Long userId);
}
