package org.tcpx.sharine.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tcpx.sharine.entity.VideoCategory;

import java.util.List;

public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {
    @Query("SELECT v FROM VideoCategory v WHERE v.categoryId = :categoryId")
    List<VideoCategory> findByCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT count(v) FROM VideoCategory v WHERE v.categoryId = :categoryId")
    Long countByCategoryId(Long categoryId);
}
