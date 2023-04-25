package com.example.ProjectBeehive.service;

import com.example.ProjectBeehive.entity.User;
import com.example.ProjectBeehive.security.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Optional;

public interface UserService {

    Page<UserResponse> findAll(Pageable pageable);

    UserResponse convertToDTO(User user);

    Optional<User> findById(BigInteger id);

    Optional<User> findByUsername(String username);

    boolean deleteById(String username);

    boolean existsById(BigInteger id);

    UserDetails loadUserByUsername(String username);

    BigInteger findIdByUsername(String username);
}
