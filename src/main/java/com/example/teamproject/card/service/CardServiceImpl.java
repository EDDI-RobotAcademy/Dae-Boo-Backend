package com.example.teamproject.card.service;


import com.example.teamproject.card.entity.CardDetail;
import com.example.teamproject.card.repository.CardDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    final private CardDetailRepository cardDetailRepository;


    @Override
    public List<CardDetail> getcard() {
        List<CardDetail> maybeCard = cardDetailRepository.findAll();
        if (maybeCard.isEmpty()) {
            return null;
        }
        return maybeCard;
    }
}
