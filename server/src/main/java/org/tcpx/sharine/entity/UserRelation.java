package org.tcpx.sharine.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.tcpx.sharine.constants.DatabaseConstants;
import org.tcpx.sharine.enums.UserRelationEnum;

/**
 * 用户关注关系
 */
@Entity
@Data
@Table(name = DatabaseConstants.FOLLOW)
public class UserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 两个用户id, 指示小的userId为userId1
    @Column(nullable = false)
    Long userId2;
    @Column(nullable = false)
    Long userId1;

    @Column(nullable = false)
    UserRelationEnum status;

    @CreationTimestamp
    Long createTime;

    @UpdateTimestamp
    Long updateTime;
}
