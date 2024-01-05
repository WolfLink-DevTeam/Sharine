package org.wolflink.sharine.action;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wolflink.sharine.entity.Category;
import org.wolflink.sharine.entity.Video;
import org.wolflink.sharine.entity.VideoMetadata;
import org.wolflink.sharine.repository.CategoryRepository;
import org.wolflink.sharine.repository.VideoMetadataRepository;
import org.wolflink.sharine.vo.VideoVO;

/**
 * 对象属性聚合转换操作
 * Entity -> VO 并补全缺失的复杂参数
 */
@Component
@AllArgsConstructor
public class AggregateParseAction {

    private final BeanCopyAction beanCopyAction;
    private final VideoMetadataRepository videoMetadataRepository;
    private final CategoryRepository categoryRepository;

    public VideoVO parse(Video video) {
        VideoVO videoVO = beanCopyAction.copyObject(video,VideoVO.class);
        VideoMetadata metadata = videoMetadataRepository.findByVideoId(video.getId());
        Category category = categoryRepository.findById(video.getCategoryId()).orElseThrow();
        videoVO.setLikes(metadata.getLikes());
        videoVO.setBookmarks(metadata.getBookmarks());
        videoVO.setShares(metadata.getShares());
        videoVO.setViews(metadata.getViews());
        videoVO.setCategory(category);
        return videoVO;
    }
}
