package com.example.springlastproject.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookRequest {

  @Getter
  @Setter
  @ToString
  public static class BookDetailDTO{
    Integer bookId;
    Integer userId;
    
    public BookDetailDTO(Integer bookId, Integer userId) {
      this.bookId = bookId;
      this.userId = userId;
    }

    
  }

}