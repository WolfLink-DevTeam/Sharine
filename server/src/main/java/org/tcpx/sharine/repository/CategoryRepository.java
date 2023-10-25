package org.tcpx.sharine.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByTitleRegex(String title, Pageable pageable);

    Long countByTitleRegex(String title);
}
