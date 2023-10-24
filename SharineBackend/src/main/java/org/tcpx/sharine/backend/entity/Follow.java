package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long id;
    @Column(name = "user_id_2",nullable = false)
    long userId2;
    @Column(name = "user_id_1",nullable = false)
    long userId1;
    @Column(name = "create_time",nullable = false)
    long createTime;
    @Column(name = "update_time",nullable = false)
    long updateTime;
}
