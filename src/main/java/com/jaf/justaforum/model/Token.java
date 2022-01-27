package com.jaf.justaforum.model;

import java.time.LocalDateTime;
import java.util.UUID;

//model odzwierciedlający token znajdujący się w bazie danych
public class Token {
    private Long id;
    private String token;
    private LocalDateTime createdDate;
    private Long userId;

    //konstruktor klasy Token
    public Token(Long id, String token, LocalDateTime createdDate, Long userId) {
        this.id = id;
        this.token = token;
        this.createdDate = createdDate;
        this.userId = userId;
    }
    
	 //konstruktor klasy Token o podanym id usera
    public Token(Long userId) {
        this.token = UUID.randomUUID().toString();
        this.createdDate = LocalDateTime.now();
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
