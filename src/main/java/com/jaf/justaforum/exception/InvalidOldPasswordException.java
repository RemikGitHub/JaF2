package com.jaf.justaforum.exception;

public class InvalidOldPasswordException extends Exception{

    //exception zwracany, gdy podaliśmy nieprawidłowe stare hasło
    public InvalidOldPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
