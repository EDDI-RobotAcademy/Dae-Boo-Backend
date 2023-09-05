package com.example.teamproject.board_management.service;

import com.example.teamproject.board_management.controller.form.BoardRequestForm;
import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.user.entity.User;

import java.util.List;

public interface BoardService {
    List<Board> list();

    boolean delete(List<Long> boardId);

    //User로 게시물을 다 찾음
    List<Board> findBoardByLoginUser(User LoginUser);

    void register(BoardRequestForm boardRequestForm);

    Board read(Long boardId);

    boolean myPageBoardDelete(Long boardId, Long userId);
}
