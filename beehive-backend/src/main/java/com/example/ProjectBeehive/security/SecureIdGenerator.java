package com.example.ProjectBeehive.security;

import com.example.ProjectBeehive.repository.UserRepository;
import com.example.ProjectBeehive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.UUID;

@Component
public class SecureIdGenerator {

    private final UserService userService;

    @Autowired
    public SecureIdGenerator(UserService userService) {
        this.userService = userService;
    }

    public BigInteger generateSecureID() {
        BigInteger id;
        do {
            UUID uuid = UUID.randomUUID();
            BigInteger mostSignificantBits = BigInteger.valueOf(uuid.getMostSignificantBits());
            BigInteger leastSignificantBits = BigInteger.valueOf(uuid.getLeastSignificantBits());
            id = mostSignificantBits.shiftLeft(64).or(leastSignificantBits);
        } while (userService.existsById(id));
        return id;
    }

}
