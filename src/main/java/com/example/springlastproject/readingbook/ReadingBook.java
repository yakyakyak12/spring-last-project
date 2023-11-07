package com.example.springlastproject.readingbook;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.example.springlastproject.book.Book;
import com.example.springlastproject.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "readingbook_tb")
@Entity
public class ReadingBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer scroll; // 스크롤 위치

    @CreationTimestamp // now
    private Timestamp createdAt; // 작성일

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Builder
    public ReadingBook(Integer id, Integer scroll, Timestamp createdAt, User user, Book book) {
        this.id = id;
        this.scroll = scroll;
        this.createdAt = createdAt;
        this.user = user;
        this.book = book;
    }

}
