package com.jaf.justaforum.exception;

public class InvalidCommentContentException extends Exception{

    ////exception zwracany, gdy podaliśmy nieprawidłową treść komentarza
    public InvalidCommentContentException(String errorMessage) {
        super(errorMessage);
    }
}
