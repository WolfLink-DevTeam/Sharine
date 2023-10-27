package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tcpx.sharine.entity.Favorite;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.videoId IN :videoIds")
    Long countByVideoIdIn(List<Long> videoIds);
}
