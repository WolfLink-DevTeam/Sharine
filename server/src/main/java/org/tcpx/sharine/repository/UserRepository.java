package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
