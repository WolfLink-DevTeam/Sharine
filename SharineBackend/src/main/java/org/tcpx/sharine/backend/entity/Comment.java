package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long id;
    @Column(name = "reply_id",nullable = false)
    String replyId;
    @Column(name = "video_id",nullable = false)
    String videoId;
    @Column(nullable = false)
    String content;
    @Column(name = "create_time",nullable = false)
    long createTime;
    @Column(name = "update_time",nullable = false)
    long updateTime;
}
