package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 用户关注关系
 */
@Entity
@Data
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 两个用户id, 指示小的userId为userId1
    @Column(name = "user_id_2", nullable = false)
    Long userId2;
    @Column(name = "user_id_1", nullable = false)
    Long userId1;

    @CreationTimestamp
    @Column(name = "create_time")
    Long createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    Long updateTime;
}
