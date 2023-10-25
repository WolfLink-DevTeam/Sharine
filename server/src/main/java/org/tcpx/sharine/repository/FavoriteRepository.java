package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
