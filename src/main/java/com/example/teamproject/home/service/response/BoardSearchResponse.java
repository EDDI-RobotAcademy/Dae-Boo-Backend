package com.example.teamproject.home.service.response;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchResponse {

    private long boardId;
    private String boardName;
    private String nickname;
    private String contents;

    public static BoardSearchResponse from(Board board) {
        return new BoardSearchResponse(
                board.getBoardId(),
                board.getBoardName(),
                board.getUserId().getNickname(),
                board.getContent()
        );
    }
}
