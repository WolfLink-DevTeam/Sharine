package org.tcpx.sharine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tcpx.sharine.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
