package com.jaf.justaforum.exception;

public class NotAuthorizedException extends Exception{

    //exception zwracany, gdy nie posiadamy uprawnień do danej czynności
    public NotAuthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
