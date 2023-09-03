package com.example.teamproject.board_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class BoardStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardStateId;

    @OneToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @Setter
    // state 필드의 초기값을 1로 설정
    private Integer state = 1;

}
