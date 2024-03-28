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

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn
    Video video;

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

    @Column(nullable = false)
    @UpdateTimestamp
    Date updateTime;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    Date createTime;

}
