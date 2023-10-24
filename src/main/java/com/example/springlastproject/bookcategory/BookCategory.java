package com.example.springlastproject.bookcategory;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.springlastproject.book.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "bookcategory_tb")
@Entity
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String synthesis; // 종합 (findAll로 보이기)

    private String trend; // 트랜드

    private String life; // 라이프

    private String healing; // 힐링

    private String refinement; // 지적교양

    private String novel; // 소설

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Builder
    public BookCategory(Integer id, String synthesis, String trend, String life, String healing, String refinement,
            String novel, Book book) {
        this.id = id;
        this.synthesis = synthesis;
        this.trend = trend;
        this.life = life;
        this.healing = healing;
        this.refinement = refinement;
        this.novel = novel;
        this.book = book;
    }

}