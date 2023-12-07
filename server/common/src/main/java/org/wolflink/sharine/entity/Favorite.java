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
 * 点赞
 */
@Entity
@Data
@Table(name = DatabaseConst.FAVOURITE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long videoId;

    @Column(nullable = false)
    Long userId;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;
}
