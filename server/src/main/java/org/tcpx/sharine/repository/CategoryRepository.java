package org.tcpx.sharine.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tcpx.sharine.constants.DatabaseConstants;
import org.tcpx.sharine.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(nativeQuery = true,value = "SELECT c FROM Category c WHERE c.title REGEXP BINARY :regex")
    List<Category> findAllByTitleRegex(@Param("regex") String regex, Pageable pageable);
    @Query(nativeQuery = true,value = "SELECT COUNT(1) FROM category c WHERE c.title REGEXP BINARY :regex")
    Long countByTitleRegex(@Param("regex") String regex);
}
