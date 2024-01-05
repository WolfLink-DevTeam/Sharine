package org.wolflink.sharine.action;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.dto.UploadVideoDTO;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.entity.Video;

@Component
@AllArgsConstructor
public class DTOParseAction {
    private final BeanCopyAction beanCopyAction;
    public Comment parse(CommentDTO commentDTO) {
        return beanCopyAction.copyObject(commentDTO, Comment.class);
    }
    public Video parse(UploadVideoDTO uploadVideoDTO) {
        return beanCopyAction.copyObject(uploadVideoDTO,Video.class);
    }
}
