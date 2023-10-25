package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Calendar;

/**
 * 视频
 */
@Entity
@Data
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id",nullable = false)
    Long userId;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String tags;

    @Column(nullable = false)
    String url;

    @Column(name = "cover_url",nullable = false)
    String coverUrl;

    @UpdateTimestamp
    @Column(name = "update_time")
    long updateTime;

    @CreationTimestamp
    @Column(name = "create_time")
    long createTime;
}
