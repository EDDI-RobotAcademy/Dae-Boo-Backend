package com.example.teamproject.questionBoard.dto;

import com.example.teamproject.questionBoard.entity.Question;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class QuestionDetailBoardResponse {

    final private Question questBoard;

    public QuestionDetailBoardResponse(Question questBoard) {
        this.questBoard = questBoard;
    }
}
