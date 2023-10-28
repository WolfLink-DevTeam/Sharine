package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.VideoTag;

public interface VideoTagRepository extends JpaRepository<VideoTag, Long> {
}
