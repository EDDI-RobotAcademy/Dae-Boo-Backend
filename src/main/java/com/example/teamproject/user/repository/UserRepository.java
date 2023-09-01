package com.example.teamproject.user.repository;

import com.example.teamproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

//    @Query("select r from User r where r.userId like :userId")
    Optional<User> findByUserId(Long userId);
}

