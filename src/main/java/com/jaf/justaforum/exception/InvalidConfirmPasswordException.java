package com.jaf.justaforum.exception;

public class InvalidConfirmPasswordException extends Exception{

    //exception zwracany, gdy hasła w polach "hasło" oraz "potwierdź hasło" się nie zgadzają
    public InvalidConfirmPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
