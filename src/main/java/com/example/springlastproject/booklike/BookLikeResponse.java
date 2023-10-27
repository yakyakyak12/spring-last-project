package com.example.springlastproject.booklike;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookLikeResponse {

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        Integer userId;
        Integer bookId;

        public saveDTO(BookLike bookLike) {
            this.userId = bookLike.getUser().getId();
            this.bookId = bookLike.getBook().getId();
        }

    }
}
