package com.example.springlastproject.storycategory;

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
@Table(name = "storycategory_tb")
@Entity
public class StoryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String romance; // 로맨스/BL

    private String fantasy; // 판타지/무협

    private String general; // 일반소설

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Builder
    public StoryCategory(Integer id, String romance, String fantasy, String general, Book book) {
        this.id = id;
        this.romance = romance;
        this.fantasy = fantasy;
        this.general = general;
        this.book = book;
    }

}
