package com.example.teamproject.board_management.entity;

import com.example.teamproject.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private CardCategory category;
    private String boardName;
    private String content;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User userId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime boardRegisterDate;

    @Setter
    @Column(name = "activate", columnDefinition = "boolean default true")
    private Boolean activate = true;

    public Board(CardCategory category, String boardName, String content, User userId) {
        this.category = category;
        this.boardName = boardName;
        this.content = content;
        this.userId = userId;
    }
}
