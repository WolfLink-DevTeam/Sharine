package org.wolflink.sharine.api.repository;

import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wolflink.sharine.api.constants.DatabaseConst;
import org.wolflink.sharine.api.entity.UserRelation;

import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.USER_RELATION)
public interface UserRelationRepository extends JpaRepository<UserRelation, Long> {

    @Modifying
    @Transactional
    void deleteByUserId1AndUserId2(Long userId1,Long userId2);
    @Query("SELECT COUNT(f) FROM UserRelation f " +
            "WHERE (f.userId1 = :userId AND f.status = 1) " +
            "OR (f.userId2 = :userId AND f.status = 2)")
    @Cacheable(unless = "#result==null")
    Integer countUserFollowing(Long userId);

    @Query("SELECT COUNT(f) FROM UserRelation f " +
            "WHERE (f.userId1 = :userId AND f.status = 2) " +
            "OR (f.userId2 = :userId AND f.status = 1)")
    @Cacheable(unless = "#result==null")
    Integer countUserFollowed(Long userId);

    @Query("SELECT f FROM UserRelation f " +
            "WHERE (f.userId1 = :userId AND f.status = 2) " +
            "OR (f.userId2 = :userId AND f.status = 1)")
    List<UserRelation> findAllUserFollowed(Long userId);
}
