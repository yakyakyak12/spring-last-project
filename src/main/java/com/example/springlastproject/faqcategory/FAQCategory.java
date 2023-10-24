package com.example.springlastproject.faqcategory;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.springlastproject.book.Book;
import com.example.springlastproject.faq.FAQ;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "faqcategory_tb")
@Entity
public class FAQCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subTitle; // 제목

    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    private FAQ faq; // 고객센터 (제목)

    @Builder
    public FAQCategory(Integer id, String subTitle, String content, FAQ faq) {
        this.id = id;
        this.subTitle = subTitle;
        this.content = content;
        this.faq = faq;
    }

}
