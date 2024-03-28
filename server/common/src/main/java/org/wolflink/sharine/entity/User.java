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
import org.wolflink.sharine.constant.UserConst;

import java.util.Date;

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
    String email;

    /**
     * bcrypt加密
     */
    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    @ColumnDefault(UserConst.DEFAULT_NICKNAME)
    String nickname;

    @Column(nullable = false)
    @ColumnDefault(UserConst.DEFAULT_AVATAR)
    String avatar;

    @Column(nullable = false)
    @ColumnDefault(UserConst.DEFAULT_CONTENT)
    String content;

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_metadata_id")
    UserMetadata userMetadata;

    @Column(nullable = false)
    @UpdateTimestamp
    Date updateTime;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    Date createTime;
}
