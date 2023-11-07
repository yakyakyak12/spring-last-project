package com.example.springlastproject.readingbook;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReadingBookResponse {

    @Getter
    @Setter
    public static class readingbookDTO {
        private Integer scroll;
        private List<String> bookdata;

        public readingbookDTO(List<String> bookdata, Integer scroll) {
            this.scroll = scroll;
            this.bookdata = bookdata;
        }

    }

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        private Integer id;
        private Integer userId;
        private Integer bookId;
        private Integer scroll;

        public saveDTO(ReadingBook readingBook) {
            this.id = readingBook.getId();
            this.userId = readingBook.getUser().getId();
            this.bookId = readingBook.getBook().getId();
            this.scroll = readingBook.getScroll();

        }

    }

}
