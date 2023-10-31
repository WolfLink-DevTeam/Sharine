package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tcpx.sharine.constants.DatabaseConst;
import org.tcpx.sharine.dto.CommentDTO;
import org.tcpx.sharine.utils.BeanCopyUtils;

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
    Long replyId;

    @Column(nullable = false)
    Long videoId;

    @Column(nullable = false)
    String content;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;

    public static Comment of(CommentDTO commentDTO) {
        return BeanCopyUtils.copyObject(commentDTO, Comment.class);
    }
}
