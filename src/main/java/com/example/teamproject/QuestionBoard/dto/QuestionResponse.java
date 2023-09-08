package com.example.teamproject.QuestionBoard.dto;

import com.example.teamproject.QuestionBoard.entity.Question;
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
