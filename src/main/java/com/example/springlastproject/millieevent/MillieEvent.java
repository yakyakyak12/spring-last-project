package com.example.springlastproject.millieevent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "millieEvent_tb")
@Entity
public class MillieEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title; // 제목

    private String content; // 내용

    @Builder
    public MillieEvent(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
