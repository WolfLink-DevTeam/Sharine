package org.tcpx.sharine.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.VideoCategory;

import java.util.List;

public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {
    List<VideoCategory> findAll(Example<VideoCategory> example, PageRequest pageRequest);
}
