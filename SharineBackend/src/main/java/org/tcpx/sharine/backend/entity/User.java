package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 用户信息
 */
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String username;

    /**
     * bcrypt加密
     */
    @Column(nullable = false)
    String password;

    /**
     * 用户标签？ 以“ ”分割
     */
    @Column(name = "tags")
    String tags;

    @Column(name = "nickname", nullable = false)
    String nickname;

    @CreationTimestamp
    @Column(name = "create_time")
    Long createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    Long updateTime;
}
