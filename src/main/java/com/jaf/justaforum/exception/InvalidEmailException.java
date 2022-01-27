package com.jaf.justaforum.exception;

public class InvalidEmailException extends Exception{

    //exception zwracany, gdy podano nieprawdiłowy email
    public InvalidEmailException(String errorMessage) {
        super(errorMessage);
    }
}
