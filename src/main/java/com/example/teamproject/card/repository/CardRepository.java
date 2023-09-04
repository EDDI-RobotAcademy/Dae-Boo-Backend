package com.example.teamproject.card.repository;

import com.example.teamproject.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByActivateTrue();

    Optional<Card> findByName(String name);
}
