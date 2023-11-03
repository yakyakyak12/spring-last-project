package com.example.springlastproject.readingbook;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.errors.exception.Exception403;
import com.example.springlastproject.bookreply.BookReply;
import com.example.springlastproject.readingbook.ReadingBookRequest.saveDTO;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class ReadingBookService {

    private final ReadingBookJPARepository readingBookJPARepository;

    public ReadingBookResponse.saveDTO 읽고있는책등록(saveDTO saveDTO) {
        ReadingBook readingBook = readingBookJPARepository.save(saveDTO.toEntity());
        return new ReadingBookResponse.saveDTO(readingBook);
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
