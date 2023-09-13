package com.example.teamproject.card.repository;

import com.example.teamproject.card.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish,Long> {
    @Query("select w from Wish w join fetch w.card wc join fetch w.user wu where wc.cardId = :cardId and wu.userId = :userId")
    Optional<Wish> findByUserAndCard(Long userId, Long cardId);

    @Query("select w from Wish w join fetch w.card wc join fetch w.user wu where wu.userId = :userId")
    List<Wish> findAllByUserId(Long userId);
}
