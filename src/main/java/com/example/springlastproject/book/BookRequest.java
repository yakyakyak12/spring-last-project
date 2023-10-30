package com.example.springlastproject.book;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookRequest {

  @Getter
  @Setter
  @ToString
  public static class BookDetailDTO {
    private Integer bookId;
    private Integer userId;

    public BookDetailDTO(Integer bookId, Integer userId) {
      this.bookId = bookId;
      this.userId = userId;
    }

  }

  @Getter
  @Setter
  @ToString
  public static class BookListDTO {
    private Integer bookCategowryId;
    private String alignment;

    public BookListDTO(Integer bookCategowryId, String alignment) {
      this.bookCategowryId = bookCategowryId;
      this.alignment = alignment;
    }

  }

  public static class BookSearchDTO {
    private String keyword;

  }

  @Getter
  @Setter
  @ToString
  public static class BookCategoryDTO {
    private Integer bookCategowryId;
    private String alignment;
    private Integer minusMonths;

    public BookCategoryDTO(Integer bookCategowryId, String alignment, Integer minusMonths) {
      this.bookCategowryId = bookCategowryId;
      this.alignment = alignment;
      this.minusMonths = minusMonths;
    }
  }
}