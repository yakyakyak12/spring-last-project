package com.example.springlastproject.bookMark;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookMarkService {

    private final BookMarkJPARepository bookMarkJPARepository;



    public BookMarkResponse.checkDTO 책북마크(BookMarkRequest.checkDTO requestDTO) {

      BookMark bookMark = bookMarkJPARepository.findByUser_IdAndBook_IdAndScroll(requestDTO.getUserId(), requestDTO.getBookId(), requestDTO.getScroll());
        if (bookMark == null) {
            bookMarkJPARepository.save(requestDTO.toEntity());
            return new BookMarkResponse.checkDTO(1);
        } else {
            bookMarkJPARepository.deleteById(bookMark.getId());
            return new BookMarkResponse.checkDTO(-1);
        }
    }
  

  
}
