package com.jaf.justaforum.exception;

public class InvalidPostTitleException extends Exception{

    //exception zwracany, gdy podaliśmy nieprawidłowy tytuł posta
    public InvalidPostTitleException(String errorMessage) {
        super(errorMessage);
    }
}
