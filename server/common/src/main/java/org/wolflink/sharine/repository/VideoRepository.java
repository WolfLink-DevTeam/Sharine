package org.wolflink.sharine.repository;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.entity.Video;

import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.VIDEO)
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByUserId(Long userId);

}
