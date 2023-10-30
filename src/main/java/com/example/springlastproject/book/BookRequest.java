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
  public static class MonthListDTO {
    private Integer bookCategowryId;
    private String alignment;

    public MonthListDTO(Integer bookCategowryId, String alignment) {
      this.bookCategowryId = bookCategowryId;
      this.alignment = alignment;
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
    private Integer bookCategowryId;
    private String alignment;
    private Integer minusMonths;

    public BookCategoryDTO(Integer bookCategowryId, String alignment, Integer minusMonths) {
      this.bookCategowryId = bookCategowryId;
      this.alignment = alignment;
      this.minusMonths = minusMonths;
    }
  }

  @Getter
  @Setter
  @ToString
  public static class rankingListDTO {
    private Integer bookCategowryId;
    private String alignment;

    public rankingListDTO(Integer bookCategowryId, String alignment) {
      this.bookCategowryId = bookCategowryId;
      this.alignment = alignment;
    }

  }
}