package com.example.teamproject.card.service;


import com.example.teamproject.card.entity.Card;
import com.example.teamproject.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    final private CardRepository cardRepository;


    @Override
    public List<Card> getActivateCard() {
        List<Card> cardList = cardRepository.findByActivateTrue();
        return cardList;
    }
}
