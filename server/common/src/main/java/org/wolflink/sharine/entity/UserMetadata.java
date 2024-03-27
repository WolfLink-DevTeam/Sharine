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
@Table(name = DatabaseConst.USER_METADATA)
public class UserMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long userId;
    @Column(nullable = false)
    Long fans;
    @Column(nullable = false)
    Long subscribes;
    @Column(nullable = false)
    Long views;
    @Column(nullable = false)
    Long videos;
    @Column(nullable = false)
    Long comments;
    @UpdateTimestamp
    Long updateTime;
    @CreationTimestamp
    Long createTime;
}
