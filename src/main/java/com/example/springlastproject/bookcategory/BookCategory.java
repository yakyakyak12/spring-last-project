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

    @Builder
    public BookCategory(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

}