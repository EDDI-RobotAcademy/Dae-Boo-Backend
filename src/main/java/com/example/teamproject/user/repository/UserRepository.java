package com.example.teamproject.user.repository;

import com.example.teamproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
//    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(Long userId);
    List<User> findByActivateTrue();
}

