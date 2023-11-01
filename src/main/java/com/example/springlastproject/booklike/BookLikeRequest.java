package com.example.springlastproject.booklike;

import java.sql.Timestamp;

import com.example.springlastproject.book.Book;
import com.example.springlastproject.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookLikeRequest {

    @Getter
    @Setter
    @ToString
    public static class checkDTO {
        private Integer userId;
        private Integer bookId;

        public checkDTO() {
        }

        public BookLike toEntity() {
            return BookLike.builder()
                    .user(User.builder().id(userId).build())
                    .book(Book.builder().id(bookId).build())
                    .build();

        }
    }

}