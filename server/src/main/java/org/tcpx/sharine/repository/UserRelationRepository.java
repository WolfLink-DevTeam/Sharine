package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tcpx.sharine.entity.UserRelation;

public interface UserRelationRepository extends JpaRepository<UserRelation, Long> {

    @Query("SELECT COUNT(f) FROM UserRelation f " +
            "WHERE (f.userId1 = :userId AND f.status = 1) " +
            "OR (f.userId2 = :userId AND f.status = 2)")
    Long countUserFollowing(Long userId);

    @Query("SELECT COUNT(f) FROM UserRelation f " +
            "WHERE (f.userId1 = :userId AND f.status = 2) " +
            "OR (f.userId2 = :userId AND f.status = 1)")
    Long countUserFollowed(Long userId);
}
