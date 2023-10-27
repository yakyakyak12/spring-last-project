package com.example.springlastproject.bookreply;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.utils.JwtTokenUtils;
import com.example.springlastproject.bookreply.BookReplyResponse.saveDTO;
import com.example.springlastproject.user.User;
import com.example.springlastproject.user.UserJPARepository;

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

    public void 댓글삭제(Integer id, HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JwtTokenUtils.verify(jwt);
        bookReplyJPARepository.findById(id).orElseThrow(() -> new Exception400("댓글 정보가 없습니다."));
        bookReplyJPARepository.deleteById(id);
    }

}
