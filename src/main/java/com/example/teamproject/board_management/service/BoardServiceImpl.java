package com.example.teamproject.board_management.service;

import com.example.teamproject.board_management.controller.form.BoardRequestForm;
import com.example.teamproject.board_management.entity.Board;
import com.example.teamproject.board_management.entity.BoardStatus;
import com.example.teamproject.board_management.repository.BoardRepository;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
        return boardRepository.findActiveBoards();
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
        BoardStatus boardStatus = board.getBoardStatus();

        if (boardStatus != null) {
            boardStatus.setState(0); // 0은 비활성을 뜻함
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

}
