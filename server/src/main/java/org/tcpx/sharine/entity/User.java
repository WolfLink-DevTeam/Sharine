package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tcpx.sharine.constants.DatabaseConstants;

/**
 * 用户信息
 */
@Entity
@Data
@Table(name = DatabaseConstants.USER)
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
    String tags;

    @Column(nullable = false)
    String nickname;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;
}
