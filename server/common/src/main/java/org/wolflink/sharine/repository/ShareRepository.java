package org.wolflink.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.entity.Share;
@CacheConfig(cacheNames = DatabaseConst.SHARE)
public interface ShareRepository extends JpaRepository<Share,Integer> {
}
