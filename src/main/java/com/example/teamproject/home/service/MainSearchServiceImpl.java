package com.example.teamproject.home.service;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.repository.BoardRepository;
import com.example.teamproject.card.entity.Card;
import com.example.teamproject.card.repository.CardRepository;
import com.example.teamproject.home.service.response.MainSearchResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainSearchServiceImpl implements MainSearchService {

    private final CardRepository cardRepository;
    private final BoardRepository boardRepository;

    @Override
    public MainSearchResponse search(String keyword) {
        List<Card> cards = cardRepository.findAllByKeyword(keyword);
        List<Board> boards = boardRepository.findAllByKeyword(keyword);
        return MainSearchResponse.of(cards, boards);
    }

}
