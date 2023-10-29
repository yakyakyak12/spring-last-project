package com.example.springlastproject.book;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookJPARepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b ORDER BY b.ranking DESC")
    List<Book> findTop5ByRanking();

    List<Book> findByPublicationDateBetween(Date fromDate, Date today, Sort sort);

    List<Book> findBooksInLastMonthByBookCategory_IdAndPublicationDateBetween(Integer categoryId, Date oneMonthAgo,
            Date today, Sort sort);
}
