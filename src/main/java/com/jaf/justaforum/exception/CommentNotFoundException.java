package com.jaf.justaforum.exception;

public class CommentNotFoundException extends Exception{
    
	 //exception zwracany, gdy nie znaleziono komentarza
    public CommentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
