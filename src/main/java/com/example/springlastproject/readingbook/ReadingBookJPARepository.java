package com.example.springlastproject.readingbook;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingBookJPARepository extends JpaRepository<ReadingBook, Integer> {

    List<ReadingBook> findByUserId(Integer userId);

    Optional<ReadingBook> findFirstByBookIdAndUserId(Integer bookId, Integer userId);

}
