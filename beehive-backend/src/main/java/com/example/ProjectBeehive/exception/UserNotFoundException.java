package com.example.ProjectBeehive.exception;

import java.math.BigInteger;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(BigInteger id) {
        super("Could not find user " + id);
    }
}
