package org.tcpx.sharine.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.backend.entity.Category;
import org.tcpx.sharine.backend.entity.Favorite;
import org.tcpx.sharine.backend.repository.CategoryRepository;
import org.tcpx.sharine.backend.repository.FavoriteRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "favorites")
@Service
public class FavoriteService extends BaseService<Favorite,Integer> {
    @Autowired
    public FavoriteService(FavoriteRepository repository) {
        super.repository = repository;
    }

    @Override
    @CachePut(key = "#entity.id")
    public void insert(Favorite entity) {
        super.insert(entity);
    }

    @Override
    @Cacheable(key = "#id")
    public Optional<Favorite> find(Integer id) {
        return super.find(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Integer id) {
        super.delete(id);
    }
}
