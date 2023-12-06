package org.wolflink.sharine.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.wolflink.sharine.api.entity.Comment;
import org.wolflink.sharine.api.utils.BeanCopyUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
    Long id;
    UserSimpleVO author;
    Long replyId;
    Long videoId;
    String content;
    Long createTime;
    Long updateTime;
    @SuppressWarnings("请使用相关 Service 类的 build 方法进行构造，否则会缺失参数")
    public static CommentVO of(Comment comment) {
        return BeanCopyUtils.copyObject(comment, CommentVO.class);
    }
}
