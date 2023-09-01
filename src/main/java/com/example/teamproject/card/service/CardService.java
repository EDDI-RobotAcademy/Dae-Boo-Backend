package com.example.teamproject.card.service;

import com.example.teamproject.card.entity.CardDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CardService {

    List<CardDetail> getcard();
}
