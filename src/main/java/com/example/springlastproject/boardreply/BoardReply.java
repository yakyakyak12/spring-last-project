package com.example.springlastproject.boardreply;

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
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "boardreply_tb")
@Entity
public class BoardReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content; // 내용

    @CreationTimestamp // now
    private Timestamp createdAt; // 등록일

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Builder
    public BoardReply(Integer id, String content, Timestamp createdAt, User user, Book book) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.book = book;
    }

}
