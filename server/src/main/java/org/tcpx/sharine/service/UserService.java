package org.tcpx.sharine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.entity.User;
import org.tcpx.sharine.repository.UserRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "users")
@Service
public class UserService extends BaseService<User,Integer>{

    @Autowired
    public UserService(UserRepository repository) {
        super.repository = repository;
    }

    @Override
    @CachePut(key = "#entity.id")
    public void insert(User entity) {
        super.insert(entity);
    }

    @Override
    @Cacheable(key = "#id")
    public Optional<User> find(Integer id) {
        return super.find(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Integer id) {
        super.delete(id);
    }
}
