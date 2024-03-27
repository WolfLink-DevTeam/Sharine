package org.wolflink.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.entity.UserMetadata;

@CacheConfig(cacheNames = DatabaseConst.USER_METADATA)
public interface UserMetadataRepository extends JpaRepository<UserMetadata,Long> {
    UserMetadata findByUserId(Long userId);

}
