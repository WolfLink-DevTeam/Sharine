package org.tcpx.sharine.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
