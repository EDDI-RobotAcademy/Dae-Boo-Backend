package com.example.teamproject.user.repository;

import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish,Long> {
    Optional<Wish> findByUserIdAndCardId(Long userId, Long cardId);
    List<Wish> findAllByUserId(Long userId);
}
