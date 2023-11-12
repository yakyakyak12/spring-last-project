package com.example.springlastproject.book;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.example.springlastproject.board.Board;
import com.example.springlastproject.bookcategory.BookCategory;
import com.example.springlastproject.bookdata.BookData;
import com.example.springlastproject.booklike.BookLike;
import com.example.springlastproject.bookreply.BookReply;
import com.example.springlastproject.storycategory.StoryCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "book_tb")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String picUrl; // 책 이미지

    private String title; // 책 제목

    private String writer; // 책 지은이

    private String totalPage; // 총 페이지

    private Date publicationDate; // 출판일

    @CreationTimestamp // now
    private Timestamp createdAt; // 등록일

    @Lob
    private String subTitle; // 소제목

    @Lob
    private String introduction; // 소개글

    @Lob
    private String sequence; // 목차

    @Lob
    private String writerIntroductoin; // 저자 소개

    @Lob
    private String review; // 출판사 서평

    private Integer ranking; // 인기순

    @ManyToOne(fetch = FetchType.LAZY)
    private BookCategory bookCategory; // 책 카테고리

    @ManyToOne(fetch = FetchType.LAZY)
    private StoryCategory storyCategory; // 스토리 카테고리

    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookLike> bookLikeList = new ArrayList<>(); // 북 좋아요

    @ManyToOne(fetch = FetchType.LAZY)
    private BookData bookData; // 북 내용

    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookReply> bookReplyList = new ArrayList<>(); // 북 댓글

    @Builder
    public Book(Integer id, String picUrl, String title, String writer, String totalPage, Date publicationDate,
            Timestamp createdAt, String subTitle, String introduction, String sequence, String writerIntroductoin,
            String review, Integer ranking, BookCategory bookCategory, StoryCategory storyCategory,
            List<BookLike> bookLikeList,
            BookData bookData, List<BookReply> bookReplyList) {
        this.id = id;
        this.picUrl = picUrl;
        this.title = title;
        this.writer = writer;
        this.totalPage = totalPage;
        this.publicationDate = publicationDate;
        this.createdAt = createdAt;
        this.subTitle = subTitle;
        this.introduction = introduction;
        this.sequence = sequence;
        this.writerIntroductoin = writerIntroductoin;
        this.review = review;
        this.ranking = ranking;
        this.bookCategory = bookCategory;
        this.storyCategory = storyCategory;
        this.bookLikeList = bookLikeList;
        this.bookData = bookData;
        this.bookReplyList = bookReplyList;
    }

}