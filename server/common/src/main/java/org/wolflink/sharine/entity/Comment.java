package org.wolflink.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.dto.CommentDTO;
import org.wolflink.sharine.action.BeanCopyAction;

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
}
