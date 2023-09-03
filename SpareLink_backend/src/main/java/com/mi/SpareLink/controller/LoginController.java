package com.mi.SpareLink.controller;

import com.mi.SpareLink.exception.ResourceNotFoundException;
import com.mi.SpareLink.model.User;
import com.mi.SpareLink.dto.UserDTO;
import com.mi.SpareLink.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        try {
            // Find user by username
            User user = userRepository.findByUsername(loginUser.getUsername());

            // Check if user exists
            if (user == null) {
                throw new ResourceNotFoundException("User", loginUser.getUserid());
            }

            // Compare hashed passwords
            if (passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
                // Passwords match
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getUserid());
                userDTO.setUsername(user.getUsername());
                userDTO.setRole(user.getRoleID());
                String jsonResponse = "{\"message\":\"Login successful for user: " + userDTO.getUsername()
                        + "\", \"user\":{\"username\":\"" + userDTO.getUsername() + "\", \"role\":\"" + userDTO.getRole() + "\"}}";
                return ResponseEntity.ok(jsonResponse);
            } else {
                // Passwords don't match
                return ResponseEntity.status(401).body("username or password incorrect");
            }
        } catch (Exception e) {
            // Handle any other exceptions
            return ResponseEntity.status(500).body("An error occurred");
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
