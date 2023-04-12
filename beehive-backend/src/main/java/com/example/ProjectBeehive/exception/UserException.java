package com.example.ProjectBeehive.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Component
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}

