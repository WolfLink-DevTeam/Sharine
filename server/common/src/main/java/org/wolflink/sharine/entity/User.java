package org.wolflink.sharine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constants.DatabaseConst;

/**
 * 用户信息
 */
@Entity
@Data
@Table(name = DatabaseConst.USER)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false,unique = true)
    String account;

    /**
     * bcrypt加密
     */
    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String nickname;

    @Column
    String avatar;

    @Column
    String content;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;
}
