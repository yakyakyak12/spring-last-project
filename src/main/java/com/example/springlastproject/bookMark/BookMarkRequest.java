package com.example.springlastproject.bookMark;

import com.example.springlastproject.book.Book;
import com.example.springlastproject.booklike.BookLike;
import com.example.springlastproject.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookMarkRequest {

   @Getter
    @Setter
    @ToString
    public static class checkDTO {
        private Integer userId;
        private Integer bookId;
        private Integer scroll;

        public checkDTO() {
        }

        public BookMark toEntity() {
            return BookMark.builder()
                    .scroll(scroll)
                    .user(User.builder().id(userId).build())
                    .book(Book.builder().id(bookId).build())
                    .build();

        }
    }

}