package org.tcpx.sharine.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.entity.User;

import java.util.Optional;

@CacheConfig(cacheNames = DatabaseConst.USER)
public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable(unless = "#result==null")
    @NotNull
    <S extends User> S save(@NotNull S entity);

    @Cacheable(unless = "#result==null")
    @NotNull
    Optional<User> findById(@NotNull Long userId);
    @Cacheable(unless = "#result==null")
    Optional<User> findByAccount(String account);
    Boolean existsByAccount(String account);
}
