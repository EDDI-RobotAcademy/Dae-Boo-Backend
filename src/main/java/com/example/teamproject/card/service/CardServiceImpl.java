package com.example.teamproject.card.service;


import com.example.teamproject.card.controller.form.CardRequestForm;
import com.example.teamproject.card.entity.Card;
import com.example.teamproject.card.repository.CardRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Override
    public Card cardRegister(CardRequestForm form) {
        Card newCard = form.toCard();
        Optional<Card> maybeCard = cardRepository.findByName(newCard.getName());
        if (maybeCard.isEmpty()){
            return cardRepository.save(newCard);

        }
        return null;
    }

    @Override
    public List<Card> getAgeCard() {
        RestTemplate restTemplate = new RestTemplate();
        String fastApiUrl = "http://localhost:3002/age-recommend-card";
        String response = restTemplate.getForObject(fastApiUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Card> ageCardList = new ArrayList<>();
        try {
            List<String> ageCardNumberList = objectMapper.readValue(response, new TypeReference<List<String>>() {
            });
            log.info(ageCardNumberList.toString());
            for (String cardNumber : ageCardNumberList) {
                Optional<Card> maybeCardNumber = cardRepository.findByCardId(Long.valueOf(cardNumber));
                if (maybeCardNumber.isPresent()) {
                    ageCardList.add(maybeCardNumber.get());
                } else {
                    log.info("없는 카드 번호 입니다.");
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return ageCardList;
    }
}
