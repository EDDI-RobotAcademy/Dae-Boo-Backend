package com.example.teamproject.main.service.response;

import com.example.teamproject.board.entity.JpaBoard;
import com.example.teamproject.board_management.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchResponse {

    private long boardId;
    private String title;
    private String writer;
    private String contents;

    public static BoardSearchResponse from(Board board) {
        return new BoardSearchResponse(
                board.getBoardId(),
                board.getTitle(),
                board.getWriter(),
                board.getContent()
        );
    }
}
