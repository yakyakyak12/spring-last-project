package com.example.springlastproject.bookMark;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject.book.BookJPARepository;
import com.example.springlastproject.bookdata.BookDataJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookMarkService {

    private final BookMarkJPARepository readingBookJPARepository;
    private final BookDataJPARepository bookDataJPARepository;
    private final BookJPARepository bookJPARepository;

  
}
