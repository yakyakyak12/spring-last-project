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
import com.example.springlastproject.bookdata.BookDataService;
import com.example.springlastproject.readingbook.ReadingBookRequest.saveDTO;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ReadingBookService {

    private final ReadingBookJPARepository readingBookJPARepository;
    private final BookDataJPARepository bookDataJPARepository;
    private final BookJPARepository bookJPARepository;

    public ReadingBookResponse.readingbookDTO 읽고있는책조회(Integer bookId, Integer userId) {
        Book book = bookJPARepository.findById(bookId).get(); // 책 조회
        Optional<ReadingBook> readingBook = readingBookJPARepository.findFirstByBookIdAndUserId(bookId, userId);
        BookData bookData = bookDataJPARepository.findById(book.getBookData().getId()).get(); // 조회된 책 내용 조회

        System.out.println("readingBook 잘 조회해?" + readingBook);
        System.out.println("bookData 잘 조회해?" + bookData.getData());
        String data = bookData.getData(); // String 타입으로 만들기 위해 꺼냄
        // List<String> splitTextIntoPages = bookDataService.splitTextIntoPages(data);
        List<String> splitTextIntoPages = TextIntoPages.splitTextIntoPages(data);
        System.out.println("splitTextIntoPages 잘 조회해?" + splitTextIntoPages);

        if (readingBook.isEmpty() || readingBook.get().getScroll() == null) {
            return new ReadingBookResponse.readingbookDTO(splitTextIntoPages, 0);
        }
        return new ReadingBookResponse.readingbookDTO(splitTextIntoPages, readingBook.get().getScroll());

    }

    public ReadingBookResponse.saveDTO 읽고있는책등록(saveDTO saveDTO) {
        if (saveDTO.getScroll() == 0) {
            saveDTO.setScroll(1);
            ReadingBook readingBook = readingBookJPARepository.save(saveDTO.toEntity());
            return new ReadingBookResponse.saveDTO(readingBook);
        } else {
            ReadingBook readingBook = readingBookJPARepository.save(saveDTO.toEntity());
            return new ReadingBookResponse.saveDTO(readingBook);
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

}
