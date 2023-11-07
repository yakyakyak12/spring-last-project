package com.example.springlastproject.readingbook;

import com.example.springlastproject.book.Book;
import com.example.springlastproject.user.User;

import lombok.Getter;
import lombok.Setter;

public class ReadingBookRequest {

    @Getter
    @Setter
    public static class saveDTO {
        private Integer userId;
        private Integer bookId;
        private Integer scroll;

        public saveDTO() {
        }

        public ReadingBook toEntity() {
            return ReadingBook.builder()
                    .user(User.builder().id(userId).build())
                    .book(Book.builder().id(bookId).build())
                    .scroll(scroll)
                    .build();
        }

    }

}