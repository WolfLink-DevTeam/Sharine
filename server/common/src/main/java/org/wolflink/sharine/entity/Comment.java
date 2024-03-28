package org.wolflink.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constant.DatabaseConst;

import java.util.Date;

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

    @Column(nullable = false)
    @UpdateTimestamp
    Date updateTime;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    Date createTime;
}
