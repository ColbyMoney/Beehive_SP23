package com.example.ProjectBeehive.service.impl;

import com.example.ProjectBeehive.entity.User;
import com.example.ProjectBeehive.exception.ResourceNotFoundException;
import com.example.ProjectBeehive.exception.UserNotFoundException;
import com.example.ProjectBeehive.repository.UserRepository;
import com.example.ProjectBeehive.security.UserResponse;
import com.example.ProjectBeehive.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : users) {
            userResponses.add(convertToDTO(user));
        }

        return new PageImpl<>(userResponses, pageable, users.getTotalElements());
    }

    public UserResponse convertToDTO(User user) {
        return new UserResponse(user.getFirstName(), user.getLastName(), user.getUsername());
    }

    public Optional<User> findById(BigInteger id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean deleteById(String username) {
        userRepository.deleteById(userRepository.findIdByUsername(username));
        return true;
    }

    public boolean existsById(BigInteger id) {
        return userRepository.existsById(id);
    }

/*    public Optional<User> changePassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, securePasswordEncoder.passwordEncoder(password));
    }*/

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username from your repository
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Return a UserDetails object with the user's information
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public BigInteger findIdByUsername(String username) {
        BigInteger id = userRepository.findIdByUsername(username);
        return id;
    }
}
