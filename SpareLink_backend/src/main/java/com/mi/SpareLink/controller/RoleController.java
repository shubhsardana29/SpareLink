package com.mi.SpareLink.controller;

import com.mi.SpareLink.exception.ResourceNotFoundException;
import com.mi.SpareLink.model.Role;
import com.mi.SpareLink.repository.RoleRepository;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/get-role")
    public ResponseEntity<?> getRole(@RequestBody Map<String, Long> requestMap) {
        Long roleId = requestMap.get("roleId");
        if (roleId != null) {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isPresent()) {
                String jsonResponse = "{\"role\":\"" + role.get().getRoleName() + "\"}";
                return ResponseEntity.ok(jsonResponse);
            } else {
                throw new ResourceNotFoundException("role");
            }
        } else {
            throw new IllegalArgumentException("roleId must be provided in the request.");
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
