package com.example.teamproject.board_management.service;

import com.example.teamproject.board_management.controller.form.BoardRequestForm;
import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.logIn.entity.User;

import java.util.List;

public interface BoardService {
    List<Board> list();

//    List<Board> myBoardList(Long userId);

    //User로 게시물을 다 찾음
    List<Board> findBoardByLoginUser(User LoginUser);

    void register(BoardRequestForm boardRequestForm);

}
