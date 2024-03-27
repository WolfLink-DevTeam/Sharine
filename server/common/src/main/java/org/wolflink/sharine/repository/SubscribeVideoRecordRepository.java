package org.wolflink.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.entity.SubscribeVideoRecord;

import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.SUBSCRIBE_VIDEO_RECORD)
public interface SubscribeVideoRecordRepository extends JpaRepository<SubscribeVideoRecord,Long> {

    List<SubscribeVideoRecord> findAllByUserId(Long userId);

}
