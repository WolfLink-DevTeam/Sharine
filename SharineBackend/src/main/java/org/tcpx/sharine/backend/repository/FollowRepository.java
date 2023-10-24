package org.tcpx.sharine.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.backend.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow,Integer> {
}
