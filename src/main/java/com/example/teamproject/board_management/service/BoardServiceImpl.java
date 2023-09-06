package com.example.teamproject.board_management.service;

import com.example.teamproject.board_management.controller.form.BoardRequestForm;
import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.repository.BoardRepository;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public List<Board> list() {
        return boardRepository.findByActivateTrue();
    }

    @Override
    public boolean delete(List<Long> boardIds) {
        boolean deletedAtLeastOneBoard = false;

        for (Long boardId : boardIds) {
            if (deleteBoard(boardId)) {
                deletedAtLeastOneBoard = true;
            }
        }

        return deletedAtLeastOneBoard;
    }

    private boolean deleteBoard(Long boardId) {
        Optional<Board> maybeBoard = boardRepository.findById(boardId);

        if(maybeBoard.isEmpty()) {
            log.info("존재하지 않는 게시판 입니다.");
            return false;
        }

        Board board = maybeBoard.get();

        if(board.getActivate() != null) {
            board.setActivate(false); // 비활성화
            boardRepository.save(board);
            return true;
        } else {
            log.error("게시판 삭제 중 오류가 발생했습니다.");
            return false;
        }
    }


    //User로 게시물을 다 찾음
    @Override
    public List<Board> findBoardByLoginUser(User LoginUser) {
        List<Board> maybeBoardList = boardRepository.findAllByUserId(LoginUser);
        log.info("maybeBoardList : " + maybeBoardList.toString());
        return maybeBoardList;
    }

    @Override
    public void register(BoardRequestForm boardRequestForm) {
        boardRepository.save(boardRequestForm.toBoard());
    }

    @Override
    public Board read(Long boardId) {
        Optional<Board> maybeBoard = boardRepository.findByBoardId(boardId);

        if(maybeBoard.isEmpty()) {
            log.info("존재하지 않는 게시물 입니다.");
            return null;
        }
        return maybeBoard.get();
    }

    @Override
    public boolean myPageBoardDelete(Long boardId, Long userId) {
        Optional<Board> maybeBoard = boardRepository.findByBoardId(boardId);

        //존재하는 게시물이 아니면?
        if (maybeBoard.isEmpty()) {
            log.info("maybeBoard is empty");
            return false;
        }
        Board board = maybeBoard.get();
        log.info("board : " + board);

        //userid가 일치하지 않으면?
        if(board.getUserId().getUserId() != userId) {
            log.info("wrong userId");
            return false;
        }

        // 존재하는 게시물이고 userId가 일치하면 비활서화
        board.setActivate(false);

        boardRepository.save(board);
        return true;
    }
}
