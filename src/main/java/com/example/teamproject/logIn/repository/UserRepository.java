package com.example.teamproject.logIn.repository;

import com.example.teamproject.logIn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);
}

