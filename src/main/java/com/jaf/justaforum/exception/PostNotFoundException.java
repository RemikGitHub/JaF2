package com.jaf.justaforum.exception;

public class PostNotFoundException extends Exception{
    
	 //exception zwracany, gdy nie znaleziono posta
    public PostNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
