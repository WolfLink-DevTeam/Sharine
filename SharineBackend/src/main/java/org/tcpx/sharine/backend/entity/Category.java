package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 分类
 */
@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title", nullable = false)
    String title;

    @CreationTimestamp
    @Column(name = "create_time")
    Long createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    Long updateTime;
}
