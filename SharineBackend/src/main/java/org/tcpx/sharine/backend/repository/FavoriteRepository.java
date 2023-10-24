package org.tcpx.sharine.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.backend.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
}
