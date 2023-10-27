package com.example.springlastproject.bookreply;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject.bookreply.BookReplyResponse.saveDTO;
import com.example.springlastproject.user.User;
import com.example.springlastproject.user.UserJPARepository;
import com.example.springlastproject.user.UserResponse;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class BookReplyService {
    private final BookReplyJPARepository bookReplyJPARepository;
    private final UserJPARepository userJPARepository;

    public saveDTO 댓글등록(BookReplyRequest.saveDTO saveDTO) {
        BookReply bookReply = bookReplyJPARepository.save(saveDTO.toEntity());
        User user = userJPARepository.findById(saveDTO.getUserId()).orElseThrow(() -> new Exception400("유저정보가 없습니다."));

        return new BookReplyResponse.saveDTO(bookReply, user);
    }

}
