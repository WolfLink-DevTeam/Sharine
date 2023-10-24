package org.tcpx.sharine.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.backend.entity.Category;
import org.tcpx.sharine.backend.entity.Comment;
import org.tcpx.sharine.backend.repository.CategoryRepository;
import org.tcpx.sharine.backend.repository.CommentRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "comments")
@Service
public class CommentService extends BaseService<Comment,Integer> {
    @Autowired
    public CommentService(CommentRepository repository) {
        super.repository = repository;
    }

    @Override
    @CachePut(key = "#entity.id")
    public void insert(Comment entity) {
        super.insert(entity);
    }

    @Override
    @Cacheable(key = "#id")
    public Optional<Comment> find(Integer id) {
        return super.find(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Integer id) {
        super.delete(id);
    }
}
