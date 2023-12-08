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

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = DatabaseConst.VIDEO_METADATA)
public class VideoMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long videoId;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long likes;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long bookmarks;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long shares;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long views;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long comments;

    @UpdateTimestamp
    Long updateTime;

    @CreationTimestamp
    Long createTime;

}
