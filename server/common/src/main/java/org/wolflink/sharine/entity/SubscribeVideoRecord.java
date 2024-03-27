package org.wolflink.sharine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constant.DatabaseConst;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = DatabaseConst.SUBSCRIBE_VIDEO_RECORD)
public class SubscribeVideoRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long userId;
    @Column(nullable = false)
    Long videoId;
    @UpdateTimestamp
    Long updateTime;
    @CreationTimestamp
    Long createTime;
}
