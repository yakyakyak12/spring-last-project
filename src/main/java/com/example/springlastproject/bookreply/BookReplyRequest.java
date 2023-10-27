package com.example.springlastproject.bookreply;

import java.sql.Timestamp;

import com.example.springlastproject.book.Book;
import com.example.springlastproject.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookReplyRequest {

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        private Integer userId;
        private Integer bookId;
        private String content;

        public BookReply toEntity() {
            return BookReply.builder()
                    .user(User.builder().id(userId).build())
                    .book(Book.builder().id(bookId).build())
                    .content(content)
                    .build();
        }

    }

}