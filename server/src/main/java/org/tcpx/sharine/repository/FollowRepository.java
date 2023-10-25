package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
