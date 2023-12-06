package org.wolflink.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constants.DatabaseConst;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.utils.BeanCopyUtils;

/**
 * 评论
 */
@Entity
@Data
@Table(name = DatabaseConst.COMMENT)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false)
    Long replyId;

    @Column(nullable = false)
    Long videoId;

    @Column(nullable = false)
    String content;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;

    @SuppressWarnings("请使用相关 Service 类的 build 方法进行构造，否则会缺失参数")
    public static Comment of(CommentDTO commentDTO) {
        return BeanCopyUtils.copyObject(commentDTO, Comment.class);
    }
}
