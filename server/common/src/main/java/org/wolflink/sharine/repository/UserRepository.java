package org.wolflink.sharine.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.entity.User;

import java.util.Optional;

@CacheConfig(cacheNames = DatabaseConst.USER)
public interface UserRepository extends JpaRepository<User, Long> {
    @NotNull
    <S extends User> S save(@NotNull S entity);

    @Cacheable(unless = "#result==null")
    @NotNull
    Optional<User> findById(@NotNull Long userId);

    @Cacheable(unless = "#result==null")
    Optional<User> findByEmail(String account);

    @Cacheable(unless = "#result==null")
    Boolean existsByEmail(String account);
}
