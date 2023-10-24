package org.tcpx.sharine.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.backend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
