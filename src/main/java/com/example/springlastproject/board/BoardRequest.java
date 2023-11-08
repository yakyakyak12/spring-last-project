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

        public updateDTO(String boardTitle, String content, Integer bookId, Integer userId) {
            this.boardTitle = boardTitle;
            this.content = content;
            this.bookId = bookId;
            this.userId = userId;
        }

    }

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        private String boardTitle;
        private String content;
        private Integer bookId;
        private Integer userId;

        public saveDTO() {
        }

        public Board toEntity() {
            return Board.builder()
                    .boardTitle(boardTitle)
                    .content(content)
                    .user(User.builder().id(userId).build())
                    .book(Book.builder().id(bookId).build())
                    .build();
        }

    }

}