package com.example.springlastproject.readingbook;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.errors.exception.Exception403;
import com.example.springlastproject.book.Book;
import com.example.springlastproject.book.BookJPARepository;
import com.example.springlastproject.bookdata.BookData;
import com.example.springlastproject.bookdata.BookDataJPARepository;
import com.example.springlastproject.readingbook.ReadingBookRequest.saveDTO;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ReadingBookService {

    private final ReadingBookJPARepository readingBookJPARepository;
    private final BookDataJPARepository bookDataJPARepository;
    private final BookJPARepository bookJPARepository;

    public ReadingBookResponse.readingbookDTO 바로읽기(Integer bookId, Integer userId) {
        Book book = bookJPARepository.findById(bookId).get(); // 책 조회
        Optional<ReadingBook> readingBook = readingBookJPARepository.findFirstByBookIdAndUserId(bookId, userId);
        BookData bookData = bookDataJPARepository.findById(book.getBookData().getId()).get(); // 조회된 책 내용 조회

        String data = bookData.getData(); // String 타입으로 만들기 위해 꺼냄
        List<String> splitTextIntoPages = TextIntoPages.splitTextIntoPages(data);

        if (readingBook.isEmpty() || readingBook.get().getScroll() == null) {
            return new ReadingBookResponse.readingbookDTO(splitTextIntoPages, 0);
        }
        return new ReadingBookResponse.readingbookDTO(splitTextIntoPages, readingBook.get().getScroll());

    }

    public ReadingBookResponse.saveDTO 읽고있는책등록(saveDTO saveDTO) {
        Optional<ReadingBook> readingBook = readingBookJPARepository.findFirstByBookIdAndUserId(saveDTO.getBookId(),
                saveDTO.getUserId());
        if (readingBook.isEmpty() || readingBook == null) {
            ReadingBook response = readingBookJPARepository.save(saveDTO.toEntity());
            return new ReadingBookResponse.saveDTO(response);
        } else {
            readingBook.get().updateScroll(saveDTO.getScroll());
            return new ReadingBookResponse.saveDTO(readingBook.get());
        }

    }

    public void 읽고있는책삭제(Integer readingbookId, Integer userId) {
        ReadingBook bookReply = readingBookJPARepository.findById(readingbookId)
                .orElseThrow(() -> new Exception400("읽고 있는 책을 찾을 수 없습니다."));
        if (bookReply.getUser().getId() == userId) {
            readingBookJPARepository.deleteById(readingbookId);
        } else {
            throw new Exception403("권한이 없습니다.");
        }
    }

    public ReadingBookResponse.MyBookDTO 내가읽고있는책목록(Integer id) {
        List<ReadingBook> readingBooks = readingBookJPARepository.findByUserId(id);
        return new ReadingBookResponse.MyBookDTO(readingBooks);
    }

}
