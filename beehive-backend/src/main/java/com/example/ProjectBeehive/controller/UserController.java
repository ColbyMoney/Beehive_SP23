package com.example.ProjectBeehive.controller;

import com.example.ProjectBeehive.exception.ResourceNotFoundException;
import com.example.ProjectBeehive.payload.JWTAuthResponse;
import com.example.ProjectBeehive.payload.LoginDto;
import com.example.ProjectBeehive.entity.User;
import com.example.ProjectBeehive.exception.UserNotFoundException;
import com.example.ProjectBeehive.payload.RegisterDto;
import com.example.ProjectBeehive.security.JwtTokenProvider;
import com.example.ProjectBeehive.security.UserResponse;
import com.example.ProjectBeehive.service.AuthService;
import com.example.ProjectBeehive.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;
    private AuthService authService;
    private JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, AuthService authService, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/getAllUsers")
    public Page<UserResponse> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size, String sortBy,
                                      @Valid @RequestHeader("Authorization") String token){
        // Remove the Bearer prefix from the token
        String authToken = token.substring(7);

        // Validate the token
        jwtTokenProvider.validateToken(authToken);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.findAll(pageable);
    }

    // PUT request to /user/123 updates user 123 with the body data (see 4. below)
/*    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@PathVariable BigInteger ID, @RequestBody User user) {
        // update user with provided data
        user.setId(ID);
        userService.save(user);
        // return success response
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/getUser/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        // retrieve user with specified username from repository
        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // return user details in response body
            return ResponseEntity.ok(user);
        } else {
            // return not found response if user not found
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam String username) {
        // delete user with specified ID from repository
        boolean deleted = userService.deleteById(username);
        if (!deleted) {
            throw new ResourceNotFoundException("User", username, BigInteger.valueOf(300));
        }
        // return success response
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    // POST request to /user/ creates a user with the ID 123 using the body data (see 4. below). The response returns the ID.
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


/*    @PutMapping("/change_password")
    public ResponseEntity<String> changePassword(@RequestParam String username, @RequestBody String password){
        userService.changePassword(username, password);
        return ResponseEntity.ok("Password Changed");
    }*/

}
