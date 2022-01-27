package com.jaf.justaforum.exception;

public class UserNotFoundException extends Exception{

    //exception zwracany, gdy nie znaleziono użytkownika
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
