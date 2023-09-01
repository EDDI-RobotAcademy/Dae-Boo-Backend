package com.example.teamproject.card.repository;

import com.example.teamproject.card.entity.CardDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDetailRepository extends JpaRepository<CardDetail, Long> {
    
}
