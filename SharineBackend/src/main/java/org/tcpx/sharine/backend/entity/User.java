package org.tcpx.sharine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long id;
    @Column(nullable = false)
    String account;
    @Column(nullable = false)
    String password;
    @Column(name = "display_name",nullable = false)
    String displayName;
    @Column(name = "registration_date",nullable = false)
    Calendar registrationDate;
    @Column(name = "last_online_date",nullable = false)
    Calendar lastOnlineDate;
}
