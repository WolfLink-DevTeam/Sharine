package org.tcpx.sharine.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tcpx.sharine.entity.VideoCategory;

import java.util.List;

public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {
    List<VideoCategory> findByCategoryId(Long categoryId, Pageable pageable);

    Long countByCategoryId(Long categoryId);
}
