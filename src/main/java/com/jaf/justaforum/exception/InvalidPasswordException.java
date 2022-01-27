package com.jaf.justaforum.exception;

public class InvalidPasswordException  extends Exception{

    //exception zwracany, gdy podano nieprawidłowe hasło
    public InvalidPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
