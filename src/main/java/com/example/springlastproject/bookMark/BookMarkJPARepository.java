package com.example.springlastproject.bookMark;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkJPARepository extends JpaRepository<BookMark, Integer> {

  List<BookMark> findByUser_IdAndBook_IdAndScroll(Integer userId, Integer bookId, Integer scroll);

  List<BookMark> findAllByBookIdAndUserId(Integer bookId, Integer userId);

}
