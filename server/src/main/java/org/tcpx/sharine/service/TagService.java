package org.tcpx.sharine.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.tcpx.sharine.dto.ConditionDTO;
import org.tcpx.sharine.dto.TagDTO;
import org.tcpx.sharine.entity.Tag;
import org.tcpx.sharine.entity.VideoTag;
import org.tcpx.sharine.repository.TagRepository;
import org.tcpx.sharine.repository.VideoTagRepository;
import org.tcpx.sharine.vo.PageVO;
import org.tcpx.sharine.vo.TagVO;
import org.tcpx.sharine.vo.VideoVO;

import java.util.List;

@Service
public class TagService {

    final TagRepository categoryRepository;

    final VideoTagRepository videoCategoryRepository;
    final VideoService videoService;

    public TagService(TagRepository categoryRepository, VideoTagRepository videoCategoryRepository, VideoService videoService) {
        this.categoryRepository = categoryRepository;
        this.videoCategoryRepository = videoCategoryRepository;
        this.videoService = videoService;
    }

    public PageVO<TagVO> find(ConditionDTO conditionDTO) {
        String title = conditionDTO.getKeywords();

        PageRequest pageRequest = PageRequest.of(conditionDTO.getCurrent(), conditionDTO.getSize());
        List<Tag> categories = categoryRepository.findAllByTitleRegex(title, pageRequest);
        List<TagVO> list = categories.stream().map(TagVO::of).toList();

        Long count = categoryRepository.countByTitleRegex(title);

        return PageVO.<TagVO>builder().total(count).list(list).build();
    }


    public PageVO<VideoVO> findVideos(ConditionDTO conditionDTO) {
        Example<VideoTag> example = Example.of(VideoTag.builder().categoryId(conditionDTO.getId()).build());
        PageRequest pageRequest = PageRequest.of(conditionDTO.getCurrent(), conditionDTO.getSize());

        List<VideoTag> videoTagList = videoCategoryRepository.findAll(example, pageRequest);
        long count = videoCategoryRepository.count(example);

        List<Long> videoIds = videoTagList.stream().map(VideoTag::getVideoId).toList();

        List<VideoVO> videoVOS = videoService.findAll(videoIds);

        return PageVO.<VideoVO>builder().total(count).list(videoVOS).build();
    }

    public Long addAll(List<TagDTO> tagVOs) {
        List<Tag> tags = tagVOs.stream().filter(tagDTO -> tagDTO.getId() != 0).map(Tag::of).toList();

        categoryRepository.saveAll(tags);

        return (long) tags.size();
    }
}
