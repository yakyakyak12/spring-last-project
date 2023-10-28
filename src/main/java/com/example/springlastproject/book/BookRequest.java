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

  @Getter
  @Setter
  @ToString
  public static class BookListDTO{
    Integer bookCategowryId;
    String alignment;


    public BookListDTO(Integer bookCategowryId, String alignment) {
      this.bookCategowryId = bookCategowryId;
      this.alignment = alignment;
    }
    
  }
}