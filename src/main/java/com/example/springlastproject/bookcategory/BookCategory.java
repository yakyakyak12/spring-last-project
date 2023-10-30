package com.example.springlastproject.bookcategory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "bookcategory_tb")
@Entity
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categoryName; // 종합 (findAll로 보이기)

    private String categoryAbout; // 카테고리 소개

    private String categoryPicUrl; // 카테고리 사진

    @Builder
    public BookCategory(Integer id, String categoryName, String categoryAbout, String categoryPicUrl) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryAbout = categoryAbout;
        this.categoryPicUrl = categoryPicUrl;
    }

}