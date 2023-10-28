package com.example.springlastproject.book;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPARepository extends JpaRepository<Book, Integer> {

    List<Book> findByPublicationDateBetween(Date fromDate, Date today, Sort sort);


    List<Book> findBooksInLastMonthByBookCategory_IdAndPublicationDateBetween(Integer categoryId, Date oneMonthAgo,
            Date today, Sort sort);
}
