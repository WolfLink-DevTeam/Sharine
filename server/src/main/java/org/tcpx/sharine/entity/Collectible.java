package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tcpx.sharine.constants.DatabaseConst;

/**
 * 收藏
 */
@Entity
@Data
@Table(name = DatabaseConst.COLLECTIBLE)
public class Collectible {
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
