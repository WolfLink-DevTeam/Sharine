package org.tcpx.sharine.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.backend.entity.User;
import org.tcpx.sharine.backend.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService extends BaseService<User,Integer>{

    private static final String cacheNames = "users";
    @Autowired
    public UserService(UserRepository repository) {
        super.repository = repository;
    }

    @Override
    @CachePut(cacheNames = cacheNames, key = "#entity.id")
    public void insert(User entity) {
        super.insert(entity);
    }

    @Override
    @Cacheable(cacheNames = cacheNames,key = "#id")
    public Optional<User> find(Integer id) {
        return super.find(id);
    }

    @Override
    @CacheEvict(cacheNames = cacheNames,key = "#id")
    public void delete(Integer id) {
        super.delete(id);
    }
}
