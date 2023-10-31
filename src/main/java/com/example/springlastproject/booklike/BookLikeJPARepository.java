package com.example.springlastproject.booklike;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLikeJPARepository extends JpaRepository<BookLike, Integer> {

    // 유저 아이디 조회
    List<BookLike> findByUserId(Integer userId);

    //
    // BookLike findByBookIdAndUserId(Integer bookId, Integer userId);

    // 유저 아이디와 북 아이디가 같은거 조회
    BookLike findFirstByBookIdAndUserId(Integer bookId, Integer userId);

}
