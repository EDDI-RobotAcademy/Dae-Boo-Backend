package com.example.teamproject.questionBoard.entity;

import com.example.teamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String content;

    private LocalDateTime createdDate = LocalDateTime.now();

    @JoinColumn(name = "questionId")
    @ManyToOne
    private Answer questionId;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User userId;
}
