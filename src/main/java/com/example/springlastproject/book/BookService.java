package com.example.springlastproject.book;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception404;
import com.example.springlastproject._core.utils.DateUtils;
import com.example.springlastproject.bookcategory.BookCategory;
import com.example.springlastproject.bookcategory.BookCategoryJPARepository;
import com.example.springlastproject.booklike.BookLike;
import com.example.springlastproject.booklike.BookLikeJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookJPARepository bookJPARepository;
    private final BookLikeJPARepository bookLikeJPARepository;
    private final BookCategoryJPARepository bookCategoryJPARepository;

    // (기능2) 책 상세보기
    public BookResponse.BookDetailPageDTO 책상세보기(Integer bookId, Integer userId) {
        Book book = bookJPARepository.findById(bookId).orElseThrow(() -> new Exception404("해당상품을 찾을수 없습니다."));
        BookLike bookLike = bookLikeJPARepository.findFirstByBookIdAndUserId(bookId, userId);
        if (bookLike == null) {
            return new BookResponse.BookDetailPageDTO(book, -1);
        } else {
            return new BookResponse.BookDetailPageDTO(book, 1);

        }

    }

    public BookResponse.BookCategoryListDTO 한달이내출간된책() {
        System.out.println("서비스 진입");
        // LocalDate fromDate = LocalDate.now().minusMonths(1);
        // LocalDate toDate = LocalDate.now();

        // List<Book> books =
        // bookJPARepository.findByPublicationDateBetween(DateUtils.convertToSqlDate(fromDate),
        // DateUtils.convertToSqlDate(toDate));

        LocalDate oneMonthAgo = LocalDate.now().minusMonths(4);
        Date fromDate = DateUtils.convertToSqlDate(oneMonthAgo);
        Date today = new Date(System.currentTimeMillis());
        Sort sort = Sort.by(Sort.Order.asc("ranking"));
        List<Book> books = bookJPARepository.findBooksInLastMonthByBookCategory_IdAndPublicationDateBetween(1,
                fromDate, today, sort);
        System.out.println("한달이내 데이터 조회가 되는건가? : " + books);
        List<BookCategory> bookCategories = bookCategoryJPARepository.findAll();
        System.out.println("조회는 잘 되는건가? : " + books.size());

        return new BookResponse.BookCategoryListDTO(bookCategories, books);

    }

}
