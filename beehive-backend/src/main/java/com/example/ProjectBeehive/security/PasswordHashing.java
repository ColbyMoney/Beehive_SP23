package com.example.ProjectBeehive.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordHashing {

    public String passwordEncoder(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(50);
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean isPasswordValid(String plaintextPassword, String hashedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(50);
        return bCryptPasswordEncoder.matches(plaintextPassword, hashedPassword);
    }

}
