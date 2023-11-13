package com.example.springlastproject.bookMark;

import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookMarkResponse {

    @Getter
    @Setter
    @ToString
    public static class BookMarkDTO {
        private Integer bookMark;
        private Integer userId;
        private Integer bookId;
        private Integer scroll;
        private String bookMarkCreatedAt;

        public BookMarkDTO(Integer bookMark, BookMark bookmark) {
            this.bookMark = bookMark;
            this.userId = bookmark.getUser().getId();
            this.bookId = bookmark.getBook().getId();
            this.scroll = bookmark.getScroll();
            this.bookMarkCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(bookmark.getCreatedAt());
        }
    }

}
