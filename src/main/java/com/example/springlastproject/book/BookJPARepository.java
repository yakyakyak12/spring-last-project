package com.example.springlastproject.book;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springlastproject.storycategory.StoryCategory;

public interface BookJPARepository extends JpaRepository<Book, Integer> {

        // 랭킹순 5가지만 조회
        List<Book> findTop5ByOrderByRankingDesc();

        // 한달이내 출간된 책 종합 인기순 조회
        List<Book> findByPublicationDateBetween(Date fromDate, Date today, Sort sort);

        // 서점베스트 (인기순으로 조회했음..)
        List<Book> findAllByOrderByRankingAsc();

        // 서점베스트 (카테고리 순 조회)
        List<Book> findByBookCategory_Id(Integer categoryId, Sort sort);

        // 한달이내 출간된 책 categoryId, sort별로 조회
        List<Book> findBooksInLastMonthByBookCategory_IdAndPublicationDateBetween(Integer categoryId, Date oneMonthAgo,
                        Date today, Sort sort);

        // 카테고리별 목록조회
        // List<Book> findByBookCategory_IdAndPublicationDateBetween(
        //                 Integer categoryId,
        //                 Date fromDate,
        //                 Date today,
        //                 Sort sort);

        // 키워드로 검색 
        @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword%")
        List<Book> findBooksByTitleContainingKeyword(@Param("keyword") String keyword);

        // 스토리 카테고리 ID별 책목록 
        List<Book> findAllByStoryCategoryId(Integer storyCategoryId);

        // 스토리 카테고리 ID별 인기순 책목록
        List<Book> findAllByStoryCategoryIdOrderByRankingAsc(Integer storyCategoryId);

        List<Book> findAllByBookCategoryId(Integer id);
}

   


