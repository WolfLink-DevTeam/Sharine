package org.wolflink.sharine.action;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.dto.UploadVideoDTO;
import org.wolflink.sharine.entity.Category;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.entity.Video;
import org.wolflink.sharine.vo.*;

@Component
@AllArgsConstructor
public class ObjectParseAction {

    private final BeanCopyAction beanCopyAction;
    public Comment parse(CommentDTO commentDTO) {
        return beanCopyAction.copyObject(commentDTO, Comment.class);
    }
    public Video parse(UploadVideoDTO uploadVideoDTO) {
        return beanCopyAction.copyObject(uploadVideoDTO,Video.class);
    }
    public CategoryVO parse(Category category) {
        return beanCopyAction.copyObject(category, CategoryVO.class);
    }
    @SuppressWarnings("请使用相关 Service 类的 build 方法进行构造，否则会缺失参数")
    public CommentVO parse(Comment comment) {
        return beanCopyAction.copyObject(comment, CommentVO.class);
    }
    @SuppressWarnings("请使用相关 Service 类的 build 方法进行构造，否则会缺失参数")
    public UserDetailVO parse(UserSimpleVO userSimpleVO) {
        return beanCopyAction.copyObject(userSimpleVO, UserDetailVO.class);
    }
    public UserSimpleVO parse(User user) {
        return beanCopyAction.copyObject(user, UserSimpleVO.class);
    }
    @SuppressWarnings("请使用相关 Service 类的 build 方法进行构造，否则会缺失参数")
    public VideoVO parse(Video video) {
        return beanCopyAction.copyObject(video, VideoVO.class);
    }
}
