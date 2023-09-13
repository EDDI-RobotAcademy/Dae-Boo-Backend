package com.example.teamproject.card.repository;

import com.example.teamproject.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByActivateTrue();

    Optional<Card> findByName(String name);

    Optional<Card> findByCardId(Long cardId);

    @Query("SELECT c FROM Card c WHERE c.activate = true AND c.name LIKE %:keyword% OR c.company LIKE %:keyword%")
    List<Card> findAllByKeyword(@Param("keyword") String keyword);

    List<Card> findTop10ByActivateTrueOrderByViewCountDesc();
}
