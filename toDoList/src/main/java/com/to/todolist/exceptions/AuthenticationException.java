package com.to.todolist.exceptions;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
