package org.wolflink.sharine.action;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.dto.UploadVideoDTO;
import org.wolflink.sharine.entity.*;
import org.wolflink.sharine.repository.CategoryRepository;
import org.wolflink.sharine.repository.VideoMetadataRepository;
import org.wolflink.sharine.vo.*;

/**
 * 对象类型转换操作
 * 负责转换 Entity -> VO 并组装缺失参数
 * 负责转换 DTO -> Entity
 */
@Component
@AllArgsConstructor
public class ObjectParseAction {

    private final BeanCopyAction beanCopyAction;
    private final VideoMetadataRepository videoMetadataRepository;
    private final CategoryRepository categoryRepository;
    public Comment parse(CommentDTO commentDTO) {
        return beanCopyAction.copyObject(commentDTO, Comment.class);
    }
    public Video parse(UploadVideoDTO uploadVideoDTO) {
        return beanCopyAction.copyObject(uploadVideoDTO,Video.class);
    }
    public CommentVO parse(Comment comment) {
        return beanCopyAction.copyObject(comment, CommentVO.class);
    }
    public UserDetailVO parse(UserSimpleVO userSimpleVO) {
        return beanCopyAction.copyObject(userSimpleVO, UserDetailVO.class);
    }
    public UserSimpleVO parse(User user) {
        return beanCopyAction.copyObject(user, UserSimpleVO.class);
    }
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
