package com.example.springlastproject.board;

import com.example.springlastproject.book.Book;
import com.example.springlastproject.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardRequest {

    @Getter
    @Setter
    @ToString
    public static class updateDTO {
        private String boardTitle;
        private String content;
        private Integer bookId;
        private Integer userId;

        public updateDTO() {
        }

    }

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        private String boardTitle;
        private String content;
        private Integer userId;
        private Integer bookId;

        public saveDTO() {
        }

        public Board toEntity() {
            return Board.builder()
                    .boardTitle(boardTitle)
                    .content(content)
                    .user(User.builder().id(userId).build())
                    .book((bookId != null) ? Book.builder().id(bookId).build() : null)
                    .build();
        }

    }

}