package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long id;
    @Column(name = "category_name",nullable = false)
    String categoryName;
    @Column(name = "create_time",nullable = false)
    long createTime;
    @Column(name = "update_time",nullable = false)
    long updateTime;
}
