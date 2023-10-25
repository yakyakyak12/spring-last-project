package com.example.springlastproject.boardlike;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.example.springlastproject.board.Board;
import com.example.springlastproject.book.Book;
import com.example.springlastproject.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "boardlike_tb")
@Entity
public class BoardLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp // now
    private Timestamp createdAt; // 등록일

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public BoardLike(Integer id, Timestamp createdAt, User user, Board board) {
        this.id = id;
        this.createdAt = createdAt;
        this.user = user;
        this.board = board;
    }

}
