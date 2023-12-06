package org.wolflink.sharine.api.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wolflink.sharine.api.constants.DatabaseConst;
import org.wolflink.sharine.api.entity.User;

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

    @Cacheable(unless = "#result==null")
    Boolean existsByAccount(String account);
}
