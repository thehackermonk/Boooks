package com.hacker.boooks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "auth")
public class AuthEntity {
    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "last_login", nullable = false)
    private Timestamp lastLogin;
    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

}