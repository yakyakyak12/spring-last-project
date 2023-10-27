package com.example.springlastproject.book;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookJPARepository extends JpaRepository<Book, Integer> {

    List<Book> findByPublicationDateBetween(Date fromDate, Date toDate);

    @Query("SELECT b FROM Book b " +
            "WHERE b.publicationDate >= :oneMonthAgo AND b.bookCategory.id = 1 " +
            "ORDER BY b.ranking ASC")
    List<Book> findBooksInLastMonthByCategory1(@Param("oneMonthAgo") LocalDate oneMonthAgo);

    List<Book> findBooksInLastMonthByBookCategory_IdAndPublicationDateBetween(Integer categoryId, Date oneMonthAgo,
            Date today, Sort sort);
}
