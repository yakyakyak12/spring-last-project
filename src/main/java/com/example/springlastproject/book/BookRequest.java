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

    public BookDetailDTO() {
    }

    public BookDetailDTO(Integer bookId, Integer userId) {
      this.bookId = bookId;
      this.userId = userId;
    }

  }

  @Getter
  @Setter
  @ToString
  public static class MonthListDTO {
    private Integer bookCategoryId;
    private String alignment;

    public MonthListDTO() {

    }

  }

  @Getter
  @Setter
  @ToString
  public static class BookSearchDTO {
    private String keyword;

    public BookSearchDTO() {
    }

    public BookSearchDTO(String keyword) {
      this.keyword = keyword;
    }

  }

  @Getter
  @Setter
  @ToString
  public static class BookCategoryDTO {
    private Integer bookCategoryId;
    private String alignment;
    private Integer minusMonths;

    public BookCategoryDTO() {
    }

    public BookCategoryDTO(Integer bookCategoryId, String alignment, Integer minusMonths) {
      this.bookCategoryId = bookCategoryId;
      this.alignment = alignment;
      this.minusMonths = minusMonths;
    }
  }

  @Getter
  @Setter
  @ToString
  public static class rankingListDTO {
    private Integer bookCategoryId;
    private String alignment;

    public rankingListDTO() {
    }

  }
}