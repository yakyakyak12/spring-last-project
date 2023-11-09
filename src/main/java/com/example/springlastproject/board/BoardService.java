package com.example.springlastproject.board;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.errors.exception.Exception403;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository;
    private final EntityManager em;

    // 게시글 상세보기
    public BoardResponse.BoardDetailPageDTO 게시글상세보기(Integer boardId) {
        Board board = boardJPARepository.findById(boardId).get();
        return new BoardResponse.BoardDetailPageDTO(board);
    }

    // 게시글 수정화면
    public BoardResponse.updatePageDTO 게시글수정화면(Integer boardId, Integer userId) {
        Board board = boardJPARepository.findById(boardId).orElseThrow(() -> new Exception403("게시글이 없습니다."));
        if (board.getUser().getId() == userId) {
            return new BoardResponse.updatePageDTO(board);
        } else {
            throw new Exception403("권한이 없습니다.");
        }
    }

    // 게시글 수정하기
    public BoardResponse.updateDTO 게시글수정하기(Integer id, @Valid BoardRequest.updateDTO updateDTO) {
        Board board = boardJPARepository.findById(id).orElseThrow(() -> new Exception400("게시글 정보가 없습니다."));
        if (board.getUser().getId() == updateDTO.getUserId()) {
            boardJPARepository.updateBoard(id, updateDTO.getBoardTitle(), updateDTO.getContent(),
                    updateDTO.getBookId());
            board.updateCreatedAt(new Timestamp(new Date().getTime()));
            em.refresh(board);
            return new BoardResponse.updateDTO(board);
        }
        throw new Exception403("권한이 없습니다.");
    }

    // 게시글 등록
    @Transactional
    public BoardResponse.saveDTO 게시글등록(BoardRequest.saveDTO saveDTO) {
        Board board = boardJPARepository.save(saveDTO.toEntity());
        return new BoardResponse.saveDTO(board);
    }

    // 게시글 삭제
    public void 게시글삭제(Integer id, Integer userId) {
        Board board = boardJPARepository.findById(id).orElseThrow(() -> new Exception400("게시글 정보가 없습니다."));
        if (board.getUser().getId() == userId) {
            boardJPARepository.deleteById(id);
        } else {
            throw new Exception403("권한이 없습니다.");
        }
    }

    public BoardResponse.BoardListDTO 게시글전체조회하기() {
        List<Board> boards = boardJPARepository.findAll();
        return new BoardResponse.BoardListDTO(boards);
    }

}
