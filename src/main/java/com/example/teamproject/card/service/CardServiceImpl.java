package com.example.teamproject.card.service;


import com.example.teamproject.card.controller.form.CardRequestForm;
import com.example.teamproject.card.entity.Card;
import com.example.teamproject.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
}
