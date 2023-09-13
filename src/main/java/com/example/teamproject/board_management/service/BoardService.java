package com.example.teamproject.board_management.service;

import com.example.teamproject.board_management.controller.form.BoardRequestForm;
import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.notice.controller.form.RequestNoticeForm;
import com.example.teamproject.notice.entity.Notice;
import com.example.teamproject.user.entity.User;

import java.util.List;

public interface BoardService {
    List<Board> list();

    boolean delete(List<Long> boardId);

    //User로 게시물을 다 찾음
    List<Board> findBoardByLoginUser(User LoginUser);

    void register(BoardRequestForm boardRequestForm);
    Board register(Board board);

    Board read(Long boardId);
    Board modify(Long boardId, BoardRequestForm boardRequestForm);

    boolean myPageBoardDelete(Long boardId, Long userId);

    void myPageBoardModify(Long boardId, BoardRequestForm boardRequestForm);
}
