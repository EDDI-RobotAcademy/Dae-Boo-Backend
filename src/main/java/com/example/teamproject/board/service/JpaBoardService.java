package com.example.teamproject.board.service;



import com.example.teamproject.board.controller.form.RequestBoardForm;
import com.example.teamproject.board.entity.JpaBoard;

import java.util.List;

public interface JpaBoardService {
    List<JpaBoard> list();

    JpaBoard register(JpaBoard jpaBoard);

    JpaBoard read(Long boardId);

    void delete(Long boardId);

    JpaBoard modify(Long boardId, RequestBoardForm requestBoardForm);
}
