package com.jaf.justaforum.exception;

public class InvalidPostContentException extends Exception{

    //exception zwracany, gdy podano nieprawidłową treść posta
    public InvalidPostContentException(String errorMessage) {
        super(errorMessage);
    }
}
