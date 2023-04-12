package com.example.ProjectBeehive.controller;

import com.example.ProjectBeehive.entity.User;
import com.example.ProjectBeehive.security.SecureIdGenerator;
import com.example.ProjectBeehive.security.UserResponse;
//import com.example.ProjectBeehive.security.UserRole;
import com.example.ProjectBeehive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecureIdGenerator secureIdGenerator;

    @GetMapping("/")
    public Page<UserResponse> getUsers(Pageable pageable){
        Page<UserResponse> users = userService.findAll(pageable);
        return users;
    }

    // POST request to /user/ creates a user with the ID 123 using the body data (see 4. below). The response returns the ID.
    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // create user with provided data
        user.setId(secureIdGenerator.generateSecureID());

        // Set the default user role
        String defaultRoles = "ROLE_USER";
        user.setRoles(defaultRoles);

        User savedUser = userService.save(user);

        // return ID of created user in response body
        return ResponseEntity.ok(savedUser.getId().toString());
    }

    // PUT request to /user/123 updates user 123 with the body data (see 4. below)
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable BigInteger id, @RequestBody User user) {
        // update user with provided data
        user.setId(id);
        userService.save(user);
        // return success response
        return ResponseEntity.ok().build();
    }

        // GET request to /user/123 returns the details of user 123
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        // retrieve user with specified ID from repository
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // return user details in response body
            return ResponseEntity.ok(user);
        } else {
            // return not found response if user not found
            return ResponseEntity.notFound().build();
        }
    }

        // DELETE request to /user/123 deletes user 123
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable BigInteger id) {
        // delete user with specified ID from repository
        userService.deleteById(id);
        // return success response
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public Page<UserResponse> findAll(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.findAll(pageable);
    }

    

}
