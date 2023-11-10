package com.example.springlastproject.faq;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlastproject._core.utils.ApiUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FAQRestController {

    private final FAQService faqService;

    @GetMapping("/faq/page")
    public ResponseEntity<?> faqPage() {
        FAQResponse.FAQResponseDTO responseDTO = faqService.카테고리FAQ페이지();
        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

}
