package com.example.springlastproject.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    @Query("SELECT b FROM Board b WHERE b.boardTitle LIKE %:keyword%")
    List<Board> findBoardsByBoardTitleContainingKeyword(@Param("keyword") String keyword);

    List<Board> findByUserId(Integer userId);

    @Modifying
    @Query("UPDATE Board b SET b.boardTitle = :newTitle, b.content = :newContent, b.book.id = :newBookId WHERE b.id = :boardId")
    @Transactional
    void updateBoard(@Param("boardId") Integer boardId, @Param("newTitle") String newTitle,
            @Param("newContent") String newContent, @Param("newBookId") Integer newBookId);
}
