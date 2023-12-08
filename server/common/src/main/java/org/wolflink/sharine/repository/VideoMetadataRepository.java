package org.wolflink.sharine.repository;


import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.entity.VideoMetadata;

@CacheConfig(cacheNames = DatabaseConst.VIDEO_METADATA)
public interface VideoMetadataRepository extends JpaRepository<VideoMetadata,Long> {
    VideoMetadata findByVideoId(Long videoId);

    @Modifying
    @Transactional
    @Query("UPDATE VideoMetadata v SET v.views = v.views + 1 WHERE v.videoId = :videoId")
    void incrementViews(Long videoId);
}
