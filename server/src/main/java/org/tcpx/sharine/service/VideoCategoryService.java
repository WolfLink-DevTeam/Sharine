package org.tcpx.sharine.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.entity.VideoCategoryRelation;
import org.tcpx.sharine.exception.ErrorException;
import org.tcpx.sharine.repository.VideoCategoryRepository;
import org.tcpx.sharine.vo.CategoryVO;

import java.util.Optional;

@Service
public class VideoCategoryService {
    final VideoCategoryRepository videoCategoryRepository;
    final CategoryService categoryService;

    public VideoCategoryService(VideoCategoryRepository videoCategoryRepository, CategoryService categoryService) {
        this.videoCategoryRepository = videoCategoryRepository;
        this.categoryService = categoryService;
    }

    public CategoryVO findVideoCategory(Long videoId) {
        VideoCategoryRelation videoCategoryRelation = VideoCategoryRelation.builder()
                .videoId(videoId)
                .build();
        Optional<VideoCategoryRelation> one = videoCategoryRepository.findOne(Example.of(videoCategoryRelation));
        if (one.isEmpty()) {
            throw new ErrorException("数据错误");
        }

        return categoryService.find(one.get().getCategoryId());
    }
}
