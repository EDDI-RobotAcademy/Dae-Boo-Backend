package com.example.teamproject.board_management.controller.form;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.entity.CardCategory;
import com.example.teamproject.logIn.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestForm {
    private CardCategory category;
    private String boardName;
    private String content;
    private User userId;

    public Board toBoard() {
        return new Board(category, boardName, content, userId);
    }
}
