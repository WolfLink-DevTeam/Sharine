package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Entity
@Data
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long id;
    @Column(name = "user_id",nullable = false)
    long userId;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String tags;
    @Column(nullable = false)
    String url;
    @Column(name = "cover_url",nullable = false)
    String coverUrl;
    @Column(name = "update_time",nullable = false)
    long updateTime;
    @Column(name = "create_time",nullable = false)
    long createTime;
}
