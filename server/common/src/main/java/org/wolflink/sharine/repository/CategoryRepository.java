package org.wolflink.sharine.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wolflink.sharine.constants.DatabaseConst;
import org.wolflink.sharine.entity.Category;

import java.util.List;

@CacheConfig(cacheNames = DatabaseConst.CATEGORY)
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.title LIKE %:title%")
    @Cacheable(unless = "#result==null")
    List<Category> findAllByTitleRegex(String title, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Category c WHERE c.title LIKE %:title%")
    @Cacheable(unless = "#result==null")
    Integer countByTitleRegex(String title);
}
