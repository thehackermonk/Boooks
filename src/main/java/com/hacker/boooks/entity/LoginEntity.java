package com.hacker.boooks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "login")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginEntity {

    @Id
    String username;
    String password;
    @Column(name = "last_login_time")
    Timestamp lastLoginTime;

}
