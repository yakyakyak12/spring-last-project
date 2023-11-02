package com.example.springlastproject.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.springlastproject._core.errors.exception.Exception400;
import com.example.springlastproject._core.utils.JwtTokenUtils;
import com.example.springlastproject.board.Board;
import com.example.springlastproject.board.BoardJPARepository;
import com.example.springlastproject.booklike.BookLike;
import com.example.springlastproject.booklike.BookLikeJPARepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository;
    private final BookLikeJPARepository bookLikeJPARepository;
    private final BoardJPARepository boardJPARepository;

    public UserResponse.JoinDTO 회원가입(UserRequest.JoinDTO requestDTO) {

        User user = userJPARepository.findByCheckUsername(requestDTO.getUsername());
        if (user != null) {
            throw new Exception400("유저네임을 사용할 수 없습니다. : " + requestDTO.getUsername());
        }
        User userPS = userJPARepository.save(requestDTO.toEntity());
        return new UserResponse.JoinDTO(userPS);

    }

    public void 중복체크(String username) {
        User user = userJPARepository.findByCheckUsername(username);
        if (user != null) {
            throw new Exception400("유저네임을 사용할 수 없습니다. : " + username);
        }
    }

    public UserResponse.LoginResponseDTO 로그인(UserRequest.LoginDTO requestDTO) {
        System.out.println("로그인 서비스 진입");
        User userPS = userJPARepository.findByUsername(requestDTO.getUsername())
                .orElseThrow(() -> new Exception400("id를 찾을 수 없습니다 : " + requestDTO.getUsername()));
        System.out.println("서비스에서 조회가 잘 되었는가? : " + userPS.getNickname());

        // 토큰 생성
        String jwt = JwtTokenUtils.create(userPS);

        return new UserResponse.LoginResponseDTO(userPS, jwt);
    }

    public UserResponse.UpdateFormDTO 개인정보수정(UserRequest.UpdateFormDTO requestDTO, Integer userId) {
        System.out.println("개인정보 수정 서비스 진입");

        User user = userJPARepository.findById(userId).get();

        user.updateEmail(requestDTO.getEmail());
        user.updateNickname(requestDTO.getNickname());
        user.updatePassword(requestDTO.getPassword());

        return new UserResponse.UpdateFormDTO(user);
    }

    public UserResponse.updatePageDTO 회원정보보기(Integer userId) {
        User user = userJPARepository.findById(userId).get();
        return new UserResponse.updatePageDTO(user);
    }

    public UserResponse.BookStatusDTO 서재이용현황(Integer userId) {
        List<BookLike> bookLikeList = bookLikeJPARepository.findByUserId(userId);
        List<Board> boardList = boardJPARepository.findByUserId(userId);
        return new UserResponse.BookStatusDTO(bookLikeList, boardList);
    }

    public void 회원탈퇴(Integer userId) {
        User user = userJPARepository.findById(userId).get();
        user.updateUsername(null);
        user.updatePassword(null);
        user.updateEmail(null);
    }

}
