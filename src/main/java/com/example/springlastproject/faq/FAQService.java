package com.example.springlastproject.faq;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject.faqcategory.FAQCategory;
import com.example.springlastproject.faqcategory.FAQCategoryJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class FAQService {

    private final FAQJPARepository faqjpaRepository;
    private final FAQCategoryJPARepository faqCategoryJPARepository;

    public FAQResponse.FAQResponseDTO 카테고리FAQ페이지() {
        List<FAQCategory> faqCategorys = faqCategoryJPARepository.findAll();
        List<FAQ> faqList = faqjpaRepository.findAll();
        return new FAQResponse.FAQResponseDTO(faqCategorys, faqList);
    }

}
