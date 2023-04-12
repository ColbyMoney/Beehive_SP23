package com.example.ProjectBeehive.service;

import com.example.ProjectBeehive.entity.User;
import com.example.ProjectBeehive.exception.UserResourceNotFoundException;
import com.example.ProjectBeehive.repository.UserRepository;
import com.example.ProjectBeehive.security.PasswordHashing;
import com.example.ProjectBeehive.security.UserResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHashing securePasswordEncoder;

    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(convertToDTO(user));
        }

        return new PageImpl<>(userResponses, pageable, users.getTotalElements());
    }

    private UserResponse convertToDTO(User user) {
        return new UserResponse(user.getId().toString(), user.getEmail(), user.getFName(), user.getLName(), user.getUsername());
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        // Hash the user's password before saving
        user.setPassword(securePasswordEncoder.passwordEncoder(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(String id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserResourceNotFoundException("User not found with id " + id));
        user.setFName(userDetails.getFName());
        user.setLName(userDetails.getLName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteById(BigInteger id) {
        userRepository.deleteById(id);
    }

    public boolean existsById(BigInteger id) {
        return userRepository.existsById(id);
    }
}
