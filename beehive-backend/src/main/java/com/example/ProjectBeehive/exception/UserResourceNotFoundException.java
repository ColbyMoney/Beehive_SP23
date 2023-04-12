package com.example.ProjectBeehive.exception;

public class UserResourceNotFoundException extends RuntimeException {
    public UserResourceNotFoundException(String message) {
        super(message);
    }
}

