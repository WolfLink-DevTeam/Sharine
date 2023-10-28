package org.tcpx.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.UserRelation;
@CacheConfig(cacheNames = DatabaseConst.USER_RELATION)
public interface UserRelationRepository extends JpaRepository<UserRelation, Long> {

    @Query("SELECT COUNT(f) FROM UserRelation f " +
            "WHERE (f.userId1 = :userId AND f.status = 1) " +
            "OR (f.userId2 = :userId AND f.status = 2)")
    @Cacheable(unless = "#result==null")
    Long countUserFollowing(Long userId);

    @Query("SELECT COUNT(f) FROM UserRelation f " +
            "WHERE (f.userId1 = :userId AND f.status = 2) " +
            "OR (f.userId2 = :userId AND f.status = 1)")
    @Cacheable(unless = "#result==null")
    Long countUserFollowed(Long userId);
}
