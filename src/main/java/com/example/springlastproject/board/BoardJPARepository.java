package com.example.springlastproject.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    @Query("SELECT b FROM Board b WHERE b.boardTitle LIKE %:keyword%")
    List<Board> findBoardsByBoardTitleContainingKeyword(@Param("keyword") String keyword);

    List<Board> findByUserId(Integer userId);
}
