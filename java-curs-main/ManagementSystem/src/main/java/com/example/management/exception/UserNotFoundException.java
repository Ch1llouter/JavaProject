package com.example.management.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not find user. ID=" + id);
    }

    public UserNotFoundException(String name) {
        super("Could not find user. Name=" + name);
    }
}
