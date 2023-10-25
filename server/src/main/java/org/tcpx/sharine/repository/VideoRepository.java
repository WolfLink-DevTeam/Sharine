package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
