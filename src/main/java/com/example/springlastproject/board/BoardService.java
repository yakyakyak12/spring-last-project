package com.example.springlastproject.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.errors.exception.Exception403;
import com.example.springlastproject.boardlike.BoardLike;
import com.example.springlastproject.boardlike.BoardLikeJPARepository;
import com.example.springlastproject.boardreply.BoardReply;
import com.example.springlastproject.boardreply.BoardReplyJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository;
    private final BoardReplyJPARepository boardReplyJPARepository;
    private final BoardLikeJPARepository boardLikeJPARepository;

    // 게시글 상세보기
    public BoardResponse.BoardDetailPageDTO 게시글상세보기(Integer boardId) {
        Board board = boardJPARepository.findById(boardId).get();
        return new BoardResponse.BoardDetailPageDTO(board);
    }

    // 게시글 등록
    @Transactional
    public BoardResponse.saveDTO 게시글등록(BoardRequest.saveDTO saveDTO) {
        Board board = boardJPARepository.save(saveDTO.toEntity());

        System.out.println("서비스 탈출직전");
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

}
