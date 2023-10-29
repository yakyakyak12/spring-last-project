package com.example.springlastproject.booklike;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookLikeResponse {

    @Getter
    @Setter
    @ToString
    public static class checkDTO {
        Integer bookLike;

        public checkDTO(Integer bookLike) {
            this.bookLike = bookLike;
        }

    }
}
