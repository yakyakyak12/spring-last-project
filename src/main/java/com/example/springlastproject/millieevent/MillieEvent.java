package com.example.springlastproject.millieevent;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.springlastproject.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "millieevent_tb")
@Entity
public class MillieEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title; // 제목

    private String content; // 내용

    private Date registrationDate; // 작성일

    private String boardPicUrl; // 이미지

    @ManyToOne
    private User user;

    @Builder
    public MillieEvent(Integer id, String title, String content, Date registrationDate, String boardPicUrl, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registrationDate = registrationDate;
        this.boardPicUrl = boardPicUrl;
        this.user = user;
    }

}
