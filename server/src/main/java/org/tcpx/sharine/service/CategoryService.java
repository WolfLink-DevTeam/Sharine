package org.tcpx.sharine.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.constants.DatabaseConstants;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.entity.Category;
import org.tcpx.sharine.entity.VideoCategory;
import org.tcpx.sharine.repository.CategoryRepository;
import org.tcpx.sharine.repository.VideoCategoryRepository;
import org.tcpx.sharine.vo.CategoryVO;
import org.tcpx.sharine.vo.PageVO;
import org.tcpx.sharine.vo.VideoVO;

import java.util.List;

@Service
@CacheConfig(cacheNames = DatabaseConstants.TAG)
public class CategoryService {

    final CategoryRepository categoryRepository;

    final VideoCategoryRepository videoCategoryRepository;
    final VideoService videoService;

    public CategoryService(CategoryRepository categoryRepository, VideoCategoryRepository videoCategoryRepository, VideoService videoService) {
        this.categoryRepository = categoryRepository;
        this.videoCategoryRepository = videoCategoryRepository;
        this.videoService = videoService;
    }

    public PageVO<CategoryVO> find(ConditionDTO conditionDTO) {
        String title = conditionDTO.getKeywords();

        PageRequest pageRequest = PageRequest.of(conditionDTO.getCurrent(), conditionDTO.getSize());
        List<Category> categories = categoryRepository.findAllByTitleRegex(title, pageRequest);
        List<CategoryVO> list = categories.stream().map(CategoryVO::of).toList();

        Long count = categoryRepository.countByTitleRegex(title);

        return PageVO.<CategoryVO>builder().total(count).list(list).build();
    }


    public PageVO<VideoVO> findVideos(ConditionDTO conditionDTO) {
        Long categoryId = conditionDTO.getId();
        PageRequest pageRequest = PageRequest.of(conditionDTO.getCurrent(), conditionDTO.getSize());

        List<VideoCategory> videoTagList = videoCategoryRepository.findByCategoryId(categoryId, pageRequest);
        long count = videoCategoryRepository.countByCategoryId(categoryId);

        List<Long> videoIds = videoTagList.stream().map(VideoCategory::getVideoId).toList();

        List<VideoVO> videoVOS = videoService.findAll(videoIds);

        return PageVO.<VideoVO>builder().total(count).list(videoVOS).build();
    }
}
