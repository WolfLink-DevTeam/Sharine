package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 收藏
 */
@Entity
@Data
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "video_id",nullable = false)
    Long videoId;

    @Column(name = "user_id",nullable = false)
    Long userId;

    @CreationTimestamp
    @Column(name = "create_time")
    Long createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    Long updateTime;
}
