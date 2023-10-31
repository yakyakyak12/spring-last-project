package com.example.springlastproject.bookreply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReplyJPARepository extends JpaRepository<BookReply, Integer> {

    List<BookReply> findByUserId(Integer userId);

}
