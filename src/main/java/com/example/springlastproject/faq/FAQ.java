package com.example.springlastproject.faq;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.springlastproject.faqcategory.FAQCategory;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "faq_tb")
@Entity
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subTitle; // 제목

    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    private FAQCategory faqCategory; // 고객센터 (제목)

    @Builder
    public FAQ(Integer id, String subTitle, String content, FAQCategory faqCategory) {
        this.id = id;
        this.subTitle = subTitle;
        this.content = content;
        this.faqCategory = faqCategory;
    }

}
