package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 评论
 */
@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "reply_id", nullable = false)
    Long replyId;

    @Column(name = "video_id", nullable = false)
    Long videoId;

    @Column(nullable = false)
    String content;

    @CreationTimestamp
    @Column(name = "create_time")
    Long createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    Long updateTime;
}
