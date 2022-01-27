package com.jaf.justaforum.dto;

//klasa wykorzystywana do przyjmowania użytkownika podczas rejestracji
public class UserRegistrationDto {
    private final String username;
    private final String email;
    private final String password;
    private final String confirmPassword;

    //konstruktor klasy UserRegistrationDto
    public UserRegistrationDto(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
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
    public String getConfirmPassword() { return confirmPassword; }
}
