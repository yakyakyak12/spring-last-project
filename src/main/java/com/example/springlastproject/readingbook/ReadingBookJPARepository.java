package com.example.springlastproject.readingbook;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingBookJPARepository extends JpaRepository<ReadingBook, Integer> {

    List<ReadingBook> findByUserId(Integer userId);

}
