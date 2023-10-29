package org.tcpx.sharine.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.entity.Category;
import org.tcpx.sharine.entity.VideoCategoryRelation;
import org.tcpx.sharine.repository.CategoryRepository;
import org.tcpx.sharine.repository.VideoCategoryRepository;
import org.tcpx.sharine.repository.VideoRepository;
import org.tcpx.sharine.vo.CategoryVO;
import org.tcpx.sharine.vo.PageVO;
import org.tcpx.sharine.vo.VideoVO;

import java.util.List;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    final VideoCategoryRepository videoCategoryRepository;
    final VideoRepository videoRepository;

    public CategoryService(CategoryRepository categoryRepository, VideoCategoryRepository videoCategoryRepository,VideoRepository videoRepository) {
        this.categoryRepository = categoryRepository;
        this.videoCategoryRepository = videoCategoryRepository;
        this.videoRepository = videoRepository;
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

        List<VideoCategoryRelation> videoCategoryRelationList = videoCategoryRepository.findByCategoryId(categoryId, pageRequest);
        // 分区视频总数
        long count = videoCategoryRepository.countByCategoryId(categoryId);

        List<Long> videoIds = videoCategoryRelationList.stream().map(VideoCategoryRelation::getVideoId).toList();
        // 查询结果
        List<VideoVO> videoVOS = videoRepository.findAllById(videoIds)
                .stream().map(VideoVO::of).toList();

        return PageVO.<VideoVO>builder().total(count).list(videoVOS).build();
    }
}
