package com.hacker.boooks.bean;

import java.time.LocalDateTime;

public class Token {

    private String token;
    private LocalDateTime generationTime;

    public Token() {
    }

    public Token(String token, LocalDateTime generationTime) {
        this.token = token;
        this.generationTime = generationTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(LocalDateTime tokenGenerationTime) {
        this.generationTime = tokenGenerationTime;
    }
}
