package org.tcpx.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.Favorite;

import java.util.List;
@CacheConfig(cacheNames = DatabaseConst.FAVOURITE)
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Long countByVideoIdIn(List<Long> videoIds);
}
