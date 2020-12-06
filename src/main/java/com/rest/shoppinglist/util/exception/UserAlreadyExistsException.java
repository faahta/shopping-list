package com.rest.shoppinglist.util.exception;

/**
 * Created by Fassil on 06/12/20.
 */
public class UserAlreadyExistsException extends Throwable {
    private String message;

    public UserAlreadyExistsException(String message) {
        super();
        this.message = message;
    }
}
