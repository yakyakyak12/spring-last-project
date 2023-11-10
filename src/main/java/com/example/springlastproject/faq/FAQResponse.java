package com.example.springlastproject.faq;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.springlastproject.faqcategory.FAQCategory;

import lombok.Getter;
import lombok.Setter;

public class FAQResponse {

    @Getter
    @Setter
    public static class FAQResponseDTO {
        List<FAQCategory> faqCategoryList;
        List<FAQDTO> faqdtoList;

        public FAQResponseDTO(List<FAQCategory> faqCategories, List<FAQ> faqdtoList) {
            this.faqCategoryList = faqCategories;
            this.faqdtoList = faqdtoList.stream().map(faq -> new FAQDTO(faq)).collect(Collectors.toList());
        }

        @Getter
        @Setter
        public class FAQDTO {
            private Integer faqCategoryId;
            private String subTitle;
            private String content;

            public FAQDTO(FAQ faq) {
                this.faqCategoryId = faq.getFaqCategory().getId();
                this.subTitle = faq.getSubTitle();
                this.content = faq.getContent();
            }

        }

    }

}
