package org.wolflink.sharine.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wolflink.sharine.entity.Comment;
import org.wolflink.sharine.action.BeanCopyAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
    Long id;
    // TODO 前端自行请求 UserService
//    UserSimpleVO author;
    Long authorId;
    Long replyId;
    Long videoId;
    String content;
    Long createTime;
    Long updateTime;

}
