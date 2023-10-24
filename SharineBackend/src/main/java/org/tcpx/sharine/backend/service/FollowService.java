package org.tcpx.sharine.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.backend.entity.Category;
import org.tcpx.sharine.backend.entity.Follow;
import org.tcpx.sharine.backend.repository.CategoryRepository;
import org.tcpx.sharine.backend.repository.FollowRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "follows")
@Service
public class FollowService extends BaseService<Follow,Integer> {
    @Autowired
    public FollowService(FollowRepository repository) {
        super.repository = repository;
    }

    @Override
    @CachePut(key = "#entity.id")
    public void insert(Follow entity) {
        super.insert(entity);
    }

    @Override
    @Cacheable(key = "#id")
    public Optional<Follow> find(Integer id) {
        return super.find(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Integer id) {
        super.delete(id);
    }
}
