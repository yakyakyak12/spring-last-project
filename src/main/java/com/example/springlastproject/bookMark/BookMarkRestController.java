package com.example.springlastproject.bookMark;


import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookMarkRestController {

    private final BookMarkService readingBookService;

}
