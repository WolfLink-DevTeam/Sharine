package org.wolflink.sharine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constant.DatabaseConst;

import java.util.Date;

/**
 * 视频
 */
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = DatabaseConst.VIDEO)
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long categoryId;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    @ColumnDefault("暂无简介")
    String content;

    @Column(nullable = false)
    String url;

    @Column(nullable = false)
    String coverUrl;

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn
    VideoMetadata videoMetadata;

    @Column(nullable = false)
    @UpdateTimestamp
    Date updateTime;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    Date createTime;

}
