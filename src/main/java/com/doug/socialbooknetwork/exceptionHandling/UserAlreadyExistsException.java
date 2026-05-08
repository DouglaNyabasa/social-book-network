package com.doug.socialbooknetwork.exceptionHandling;


public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String s) {
        super(s);
    }
}
