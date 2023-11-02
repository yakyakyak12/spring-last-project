package com.example.springlastproject.boardlike;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeJPARepository extends JpaRepository<BoardLike, Integer> {

    List<BoardLike> findByBoardId(Integer id);

}
