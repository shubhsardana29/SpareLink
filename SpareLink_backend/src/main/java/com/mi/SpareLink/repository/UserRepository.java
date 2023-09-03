package com.mi.SpareLink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mi.SpareLink.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
