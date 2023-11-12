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

  }

  // @Getter
  // @Setter
  // @ToString
  // public static class BookCategoryDTO {
  //   private Integer bookCategoryId;
  //   private String alignment;
  //   private Integer minusMonths;

  //   public BookCategoryDTO() {
  //   }

  // }

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