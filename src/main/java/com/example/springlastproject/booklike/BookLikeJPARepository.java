package com.example.springlastproject.booklike;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springlastproject.user.User;

public interface BookLikeJPARepository extends JpaRepository<BookLike, Integer> {

    BookLike findByUserId(Integer userId);

    BookLike findByBookIdAndUserId(Integer bookId, Integer userId);

    BookLike findFirstByBookIdAndUserId(Integer bookId, Integer userId);

}
