package org.tcpx.sharine.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tcpx.sharine.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
