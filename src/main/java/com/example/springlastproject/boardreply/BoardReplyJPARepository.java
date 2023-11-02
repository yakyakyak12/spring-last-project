package com.example.springlastproject.boardreply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardReplyJPARepository extends JpaRepository<BoardReply, Integer> {

    List<BoardReply> findByUserId(Integer userId);

    @Query("SELECT br, brd FROM BookReply br JOIN BoardReply brd ON br.user.id = brd.user.id WHERE br.user.id = :userId")
    List<Object[]> findRepliesWithSameUserId(Integer userId);

    List<BoardReply> findByBoardId(Integer id);
}
