package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tcpx.sharine.constants.DatabaseConst;

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
}
