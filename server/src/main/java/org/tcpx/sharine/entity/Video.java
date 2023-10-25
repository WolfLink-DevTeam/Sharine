package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tcpx.sharine.constants.DatabaseConstants;

/**
 * 视频
 */
@Entity
@Data
@Table(name = DatabaseConstants.VIDEO)
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long userId;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String url;

    @Column(nullable = false)
    String coverUrl;

    @UpdateTimestamp
    Long updateTime;

    @CreationTimestamp
    Long createTime;
}
