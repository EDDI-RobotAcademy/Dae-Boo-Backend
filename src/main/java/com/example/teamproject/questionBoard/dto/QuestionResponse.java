package com.example.teamproject.questionBoard.dto;

import com.example.teamproject.questionBoard.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

    private long questionId;
    private String title;
    private LocalDateTime createdAt;

    private boolean isAnswerComplete;

    public static QuestionResponse from(Question question) {
        return new QuestionResponse(
                question.getQuestionId(), question.getTitle(), question.getCreatedAt(), question.isAnswerComplete());
    }
}
