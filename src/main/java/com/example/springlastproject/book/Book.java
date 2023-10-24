package com.example.springlastproject.book;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

// import com.example.springlastproject.board.Board;
// import com.example.springlastproject.bookcategory.BookCategory;
// import com.example.springlastproject.booklike.BookLike;
// import com.example.springlastproject.bookreply.BookReply;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "book_tb")
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String picUrl; // 책 이미지

    private String title; // 책 제목

    private String writer; // 책 지은이

    private String content; // 내용

    private String totalPage; // 총 페이지

    private Date publicationDate; // 출판일

    @CreationTimestamp // now
    private Timestamp createdAt; // 등록일

    private String subTitle; // 소제목

    private String introduction; // 소개글

    private String sequence; // 목차

    private String writerIntroductoin; // 저자 소개

    private String review; // 출판사 서평

    public Book(Integer id, String picUrl, String title, String writer, String content, String totalPage,
            Date publicationDate, Timestamp createdAt, String subTitle, String introduction, String sequence,
            String writerIntroductoin, String review) {
        this.id = id;
        this.picUrl = picUrl;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.totalPage = totalPage;
        this.publicationDate = publicationDate;
        this.createdAt = createdAt;
        this.subTitle = subTitle;
        this.introduction = introduction;
        this.sequence = sequence;
        this.writerIntroductoin = writerIntroductoin;
        this.review = review;
    }

    // @ManyToOne
    // private BookCategory bookCategory; // 책 카테고리

    // @JsonIgnore
    // @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    // private Board feed;

    // @JsonIgnore
    // @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    // private List<BookLike> bookLikeList = new ArrayList<>();

    // @JsonIgnore
    // @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    // private List<BookReply> bookReplyList = new ArrayList<>();

}