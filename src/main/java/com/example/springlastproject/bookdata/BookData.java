package com.example.springlastproject.bookdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.springlastproject.book.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "bookdata_tb")
@Entity
public class BookData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String data; // 내용

    @Builder
    public BookData(Integer id, String data) {
        this.id = id;
        this.data = data;
    }

}
