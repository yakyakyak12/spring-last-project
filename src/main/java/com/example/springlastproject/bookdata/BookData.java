package com.example.springlastproject.bookdata;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.springlastproject.book.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "bookdata_tb")
@Entity
public class BookData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bookData; // 내용

    @OneToOne(fetch = FetchType.LAZY)
    private Book book;

    @Builder
    public BookData(Integer id, String bookData, Book book) {
        this.id = id;
        this.bookData = bookData;
        this.book = book;
    }

}
