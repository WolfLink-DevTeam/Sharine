package org.tcpx.sharine.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.Bookmark;

import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.BOOKMARK)
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @NotNull
    @CachePut(unless = "#result==null")
    <S extends Bookmark> S save(@NotNull S entity);

    @Cacheable(unless = "#result==null")
    Bookmark findByUserIdAndVideoId(Long userId, Long videoId);

    @Cacheable(unless = "#result==null")
    boolean existsByUserIdAndVideoId(Long userId, Long videoId);

    @CacheEvict
    void deleteByUserIdAndVideoId(Long userId, Long videoId);

    @Cacheable(unless = "#result==null")
    Long countByUserId(Long userId);

    @Cacheable(unless = "#result==null")
    Long countByVideoId(Long videoId);


    List<Bookmark> findByUserId(Long userId, Pageable pageable);
    List<Bookmark> findByUserId(Long userId);
}
