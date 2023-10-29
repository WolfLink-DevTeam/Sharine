package org.tcpx.sharine.repository;

import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.Bookmark;

@CacheConfig(cacheNames = DatabaseConst.BOOKMARK)
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {

    @Cacheable(unless = "#result==null")
    Long countByUserId(Long userId);
    @Cacheable(unless = "#result==null")
    Long countByVideoId(Long videoId);

}
