package com.jaf.justaforum.model;

import java.time.LocalDateTime;

//model odzwierciedlający użytkownika znajdującego się w bazie danych
public class User {
    private Long id;
    private String email;
    private String username;
    private String password;
    private LocalDateTime registrationDate;
    private short  active;

    //konstruktor klasy User
    public User(Long id, String username, String email, String password, LocalDateTime registrationDate, short  active) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.active = active;
    }

    //konstrutor klast User bez id użytkownika
    public User(String username, String email, String password, LocalDateTime registrationDate, short  active) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public short  getActive() { return active; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
