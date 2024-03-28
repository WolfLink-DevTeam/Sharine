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

import java.util.Date;

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

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long fans;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long subscribes;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long views;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long videos;

    @Column(nullable = false)
    @ColumnDefault("0")
    Long comments;

    @Column(nullable = false)
    @UpdateTimestamp
    Date updateTime;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    Date createTime;

}
