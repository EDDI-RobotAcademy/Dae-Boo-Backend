package com.example.teamproject.card.service;


import com.example.teamproject.card.entity.CardDetail;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


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
