package org.tcpx.sharine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.entity.Category;
import org.tcpx.sharine.repository.CategoryRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "categories")
@Service
public class CategoryService extends BaseService<Category,Integer> {
    @Autowired
    public CategoryService(CategoryRepository repository) {
        super.repository = repository;
    }

    @Override
    @CachePut(key = "#entity.id")
    public void insert(Category entity) {
        super.insert(entity);
    }

    @Override
    @Cacheable(key = "#id")
    public Optional<Category> find(Integer id) {
        return super.find(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Integer id) {
        super.delete(id);
    }
}
