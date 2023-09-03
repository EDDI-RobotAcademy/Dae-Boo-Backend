package com.example.teamproject.board_management.controller;

import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    final private BoardService boardService;

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
}
