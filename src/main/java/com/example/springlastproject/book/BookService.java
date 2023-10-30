package com.example.springlastproject.book;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

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

    // 책 상세보기
    public BookResponse.BookDetailPageDTO 책상세보기(BookRequest.BookDetailDTO bookDetailDTO) {
        Book book = bookJPARepository.findById(bookDetailDTO.getBookId())
                .orElseThrow(() -> new Exception404("해당상품을 찾을수 없습니다."));
        // 유저 아이디가 없으면 -1을 바로 보냄
        if (bookDetailDTO.getUserId() == null) {
            return new BookResponse.BookDetailPageDTO(book, -1);
        }
        BookLike bookLike = bookLikeJPARepository.findFirstByBookIdAndUserId(bookDetailDTO.getBookId(),
                bookDetailDTO.getUserId());
        // 로그인 한 유저일시에는 조회 한 후에 북마크 유무 확인해서 있으면 1 없으면 -1
        if (bookLike == null) {
            return new BookResponse.BookDetailPageDTO(book, -1);
        } else {
            return new BookResponse.BookDetailPageDTO(book, 1);

        }

    }

    // 한달이내 출간된 책만 보기
    public BookResponse.BookCategoryListDTO 한달이내출간된책(BookRequest.BookListDTO bookListDTO) {

        List<BookCategory> bookCategories = bookCategoryJPARepository.findAll(); // 카테고리 조회
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1); // 한달이내 요일을 지정
        Date fromDate = DateUtils.convertToSqlDate(oneMonthAgo); // LocalDate -> Date로 변환
        Date today = new Date(System.currentTimeMillis()); // 오늘 날짜
        Sort sort = Sort.by(Sort.Order.asc(bookListDTO.getAlignment())); // 정렬할꺼 정해주는거

        if (bookListDTO.getBookCategowryId() != 0) {
            List<Book> books = bookJPARepository.findBooksInLastMonthByBookCategory_IdAndPublicationDateBetween(
                    bookListDTO.getBookCategowryId(), fromDate, today, sort);
            return new BookResponse.BookCategoryListDTO(bookCategories, books, bookListDTO.getBookCategowryId());
        } else {
            List<Book> books = bookJPARepository.findByPublicationDateBetween(fromDate, today, sort);
            return new BookResponse.BookCategoryListDTO(bookCategories, books, bookListDTO.getBookCategowryId());
        }
    }

    // 제목 검색
    public void 키워드검색(BookRequest.BookSearchDTO book) {

    }

    public BookResponse.BookSearchPageDTO 검색화면() {
        List<BookCategory> bookCategories = bookCategoryJPARepository.findAll();
        return new BookResponse.BookSearchPageDTO(bookCategories);
    }

    public BookResponse.BookCategoryDTO 카테고리별목록보기(BookRequest.BookCategoryDTO bookCategoryDTO) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(bookCategoryDTO.getMinusMonths()); // 한달이내 요일을 지정
        Date fromDate = DateUtils.convertToSqlDate(oneMonthAgo); // LocalDate -> // Date로 변환
        Date today = new Date(System.currentTimeMillis()); // 오늘 날짜
        Sort sort = Sort.by(Sort.Order.asc(bookCategoryDTO.getAlignment())); // 정렬할꺼 정해주는거

        List<Book> books = bookJPARepository.findByBookCategory_IdAndPublicationDateBetween(
                bookCategoryDTO.getBookCategowryId(),
                fromDate,
                today, sort);

        return new BookResponse.BookCategoryDTO(bookCategoryDTO.getBookCategowryId(), books);
    }

}
