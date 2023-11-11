package com.example.springlastproject.bookMark;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookMarkResponse {

  @Getter
    @Setter
    @ToString
    public static class checkDTO {
        private Integer bookMark;

        public checkDTO(Integer bookMark) {
            this.bookMark = bookMark;
        }
    }



}
