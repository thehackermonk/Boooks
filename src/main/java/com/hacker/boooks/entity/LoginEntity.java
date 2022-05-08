package com.hacker.boooks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;


@Entity
@Table(name = "login")
public class LoginEntity {

    @Id
    @Column(name = "username")
    String userName;
    String password;
    String token;
    @Column(name = "token_creation_time")
    Timestamp tokenCreationTime;

    public LoginEntity() {
    }

    public LoginEntity(String userName, String password, String token, Timestamp tokenCreationTime) {
        this.userName = userName;
        this.password = password;
        this.token = token;
        this.tokenCreationTime = tokenCreationTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTokenCreationTime() {
        return tokenCreationTime;
    }

    public void setTokenCreationTime(Timestamp tokenCreationTime) {
        this.tokenCreationTime = tokenCreationTime;
    }
}
