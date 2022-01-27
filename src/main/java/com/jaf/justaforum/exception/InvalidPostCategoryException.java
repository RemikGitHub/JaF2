package com.jaf.justaforum.exception;

public class InvalidPostCategoryException extends Exception{

    //exception zwracany, gdy podano nieprawdiłową kategorię posta
    public InvalidPostCategoryException(String errorMessage) {
        super(errorMessage);
    }
}
