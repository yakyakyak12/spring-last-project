package com.example.springlastproject.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject.book.Book;
import com.example.springlastproject.book.BookJPARepository;
import com.example.springlastproject.book.BookRequest;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository;

}
