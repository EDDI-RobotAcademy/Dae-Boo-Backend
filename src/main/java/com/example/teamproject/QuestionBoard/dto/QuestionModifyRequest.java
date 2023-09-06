package com.example.teamproject.QuestionBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModifyRequest {

    private String title;
    private String contents;
}
