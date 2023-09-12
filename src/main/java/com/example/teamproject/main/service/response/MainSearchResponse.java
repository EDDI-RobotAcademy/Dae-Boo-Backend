package com.example.teamproject.main.service.response;

import com.example.teamproject.board.entity.JpaBoard;
import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.card.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainSearchResponse {

    private List<CardSearchResponse> cards;
    private List<BoardSearchResponse> Boards;

    public static MainSearchResponse of(List<Card> cards, List<Board> boards) {
        List<CardSearchResponse> cardResp = cards.stream()
                .map(CardSearchResponse::from)
                .toList();
        List<BoardSearchResponse> boardResp = boards.stream()
                .map(BoardSearchResponse::from)
                .toList();
        return new MainSearchResponse(cardResp, boardResp);
    }
}
