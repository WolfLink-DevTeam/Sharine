package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long id;
    @Column(name = "video_id",nullable = false)
    String videoId;
    @Column(name = "user_id",nullable = false)
    long userId;
    @Column(name = "create_time",nullable = false)
    long createTime;
    @Column(name = "update_time",nullable = false)
    long updateTime;
}
