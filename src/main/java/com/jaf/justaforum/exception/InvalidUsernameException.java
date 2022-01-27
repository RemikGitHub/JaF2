package com.jaf.justaforum.exception;

public class InvalidUsernameException extends Exception{

    //exception zwracany, gdy podano nieprawidłową nazwę użytkownika
    public InvalidUsernameException(String errorMessage) {
        super(errorMessage);
    }
}
