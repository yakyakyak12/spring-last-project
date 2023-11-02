package com.example.springlastproject.board;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.example.springlastproject.boardlike.BoardLike;
import com.example.springlastproject.boardreply.BoardReply;
import com.example.springlastproject.book.Book;
import com.example.springlastproject.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String boardTitle; // 제목

    private String content; // 내용

    @CreationTimestamp // now
    private Timestamp createdAt; // 등록일

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardLike> boardLikeList = new ArrayList<>(); // 게시글 좋아요

    @JsonIgnore
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardReply> boardReplyList = new ArrayList<>(); // 게시글 댓글

    @Builder
    public Board(Integer id, String boardTitle, String content, Timestamp createdAt, User user,
            Book book, List<BoardLike> boardLikeList, List<BoardReply> boardReplyList) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.book = book;
        this.boardLikeList = boardLikeList;
        this.boardReplyList = boardReplyList;
    }

}
