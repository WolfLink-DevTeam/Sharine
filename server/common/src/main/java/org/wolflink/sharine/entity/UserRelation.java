package org.wolflink.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.wolflink.sharine.constant.DatabaseConst;
import org.wolflink.sharine.enums.UserRelationEnum;

/**
 * 用户关注关系
 */
@Entity
@Data
@Table(name = DatabaseConst.FOLLOW)
public class UserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 两个用户id, 小的userId为userId1
    @Column(nullable = false)
    Long userId2;
    @Column(nullable = false)
    Long userId1;

    // 描述的是 userId1 对 userId2 的关系，例如 1 关注了 2，1 被 2 关注
    @Column(nullable = false)
    UserRelationEnum status;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;
}
