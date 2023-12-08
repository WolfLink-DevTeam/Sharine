package org.wolflink.sharine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constant.DatabaseConst;

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

    @Column
    String content;

    @Column(nullable = false)
    String url;

    @Column(nullable = false)
    String coverUrl;

    @UpdateTimestamp
    Long updateTime;

    @CreationTimestamp
    Long createTime;

}
