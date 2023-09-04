package com.example.teamproject.board_management.controller;

import com.example.teamproject.board_management.controller.form.BoardRequestForm;
import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.service.BoardService;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    final private BoardService boardService;
    final private UserService userService;

    @GetMapping("/list")
    public List<Board> boardList () {
        log.info("boardList()");
        return boardService.list();
    }

    @DeleteMapping("/delete")
    public boolean boardDelete (@RequestParam("boardId") List<Long> boardId) {
        log.info("boardId: {}", boardId);
        return boardService.delete(boardId);
    }

    @GetMapping("/myBoard")
    public List<Board> myBoardList (@RequestParam("userId") Long userId) {
//    public String myBoardList (@RequestParam("userId") Long userId) {
        log.info("myBoardList()");
        log.info("userId : " + userId);

        //useid로 User를 찾음
        User LoginUser = userService.findUserByUserId(userId);
        log.info("LoginUserId : " + LoginUser.getUserId());

        //User로 게시물을 다 찾음
        List<Board> userWrittenBoards =  boardService.findBoardByLoginUser(LoginUser);
        log.info("userWrittenBoards index_0 : " + userWrittenBoards.get(0));
        log.info("userWrittenBoards index_1 : " + userWrittenBoards.get(1));

        //프론트로 보냄
        return userWrittenBoards;
    }

    @PostMapping("/register")
    public void register (@RequestBody BoardRequestForm boardRequestForm) {
        log.info("registerBoard()");
        boardService.register(boardRequestForm);
    }

    @GetMapping("{boardId}")
    public Board personalReadBoard(@PathVariable("boardId") Long boardId) {
        log.info("personalReadBoard()");
        return boardService.read(boardId);
    }

}
