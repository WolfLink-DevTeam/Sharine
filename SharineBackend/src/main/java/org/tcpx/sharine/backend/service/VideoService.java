package org.tcpx.sharine.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.backend.entity.Category;
import org.tcpx.sharine.backend.entity.Video;
import org.tcpx.sharine.backend.repository.CategoryRepository;
import org.tcpx.sharine.backend.repository.VideoRepository;

import java.util.Optional;

@CacheConfig(cacheNames = "videos")
@Service
public class VideoService extends BaseService<Video,Integer> {
    @Autowired
    public VideoService(VideoRepository repository) {
        super.repository = repository;
    }

    @Override
    @CachePut(key = "#entity.id")
    public void insert(Video entity) {
        super.insert(entity);
    }

    @Override
    @Cacheable(key = "#id")
    public Optional<Video> find(Integer id) {
        return super.find(id);
    }

    @Override
    @CacheEvict(key = "#id")
    public void delete(Integer id) {
        super.delete(id);
    }
}
