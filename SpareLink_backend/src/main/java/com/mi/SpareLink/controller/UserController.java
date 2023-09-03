package com.mi.SpareLink.controller;

import com.mi.SpareLink.exception.ResourceNotFoundException;
import com.mi.SpareLink.model.User;
import com.mi.SpareLink.repository.UserRepository;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User User) {
        String hashedPassword = passwordEncoder.encode(User.getPassword());
        User.setPassword(hashedPassword);
        userRepository.save(User);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: " + User.getUserid());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            throw new ResourceNotFoundException("User", id);
        }
    }

    // ... Other API methods for user management

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
