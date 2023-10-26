package com.example.springlastproject.book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJPARepository extends JpaRepository<Book, Integer> {

    List<Book> findByPublicationDateBetween(LocalDate fromDate, LocalDate toDate);

}
