package com.example.springlastproject.readingbook;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReadingBookResponse {

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        private Integer readingbookId;
        private Integer userId;
        private Integer bookId;

        public saveDTO(ReadingBook readingBook) {
            this.readingbookId = readingBook.getId();
            this.userId = readingBook.getUser().getId();
            this.bookId = readingBook.getBook().getId();
        }

    }

}
