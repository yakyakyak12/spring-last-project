package com.example.springlastproject.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookResponse {

    @Getter
    @Setter
    @ToString
    public static class BookDetailDTO {
        private int id;
        private String picUrl;
        private String title;
        private String writer;
        // private String content;

        public BookDetailDTO(Book book) {
            this.id = book.getId();
            this.picUrl = book.getPicUrl();
            this.title = book.getTitle();
            this.writer = book.getWriter();
            // this.content = book.getContent();
        }
    }

    // 책 리스트
    @ToString
    @Getter
    @Setter
    public static class FindAllDTO {
        private Integer id;
        private String picUrl;
        private String title;
        private String writer;

        public FindAllDTO(Book book) {
            this.id = book.getId();
            this.picUrl = book.getPicUrl();
            this.title = book.getTitle();
            this.writer = book.getWriter();
        }
    }
}
