package com.example.springlastproject.notice;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.springlastproject.faq.FAQ;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "notice_tb")
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title; // 제목

    private String content; // 내용

    private Date registrationDate; // 작성일

    @Builder
    public Notice(Integer id, String title, String content, Date registrationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registrationDate = registrationDate;
    }

}
