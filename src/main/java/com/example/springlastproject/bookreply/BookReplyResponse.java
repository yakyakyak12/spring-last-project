package com.example.springlastproject.bookreply;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springlastproject.booklike.BookLike;
import com.example.springlastproject.user.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookReplyResponse {

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        private Integer bookReplyId;
        private Integer userId;
        private Integer bookId;
        private String userPicUrl;
        private String nickname;
        private String bookReplyContent;
        private String bookReplyCreatedAt;

        public saveDTO(BookReply bookReply, User user) {
            this.bookReplyId = bookReply.getId();
            this.userId = bookReply.getUser().getId();
            this.bookId = bookReply.getBook().getId();
            this.userPicUrl = user.getPicUrl();
            this.nickname = user.getNickname();
            this.bookReplyContent = bookReply.getContent();
            this.bookReplyCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(bookReply.getCreatedAt());
        }
    }

    @Getter
    @Setter
    @ToString
    public static class bookReplyPage {
        private List<BookLikeDTO> bookLikeList;

        public bookReplyPage(List<BookLike> bookLikeList) {
            this.bookLikeList = bookLikeList.stream().map(booklike -> new BookLikeDTO(booklike))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public class BookLikeDTO {
            private Integer bookId;
            private String bookPicUrl;
            private String bookTitle;
            private String writer;

            public BookLikeDTO(BookLike bookLike) {
                this.bookId = bookLike.getBook().getId();
                this.bookPicUrl = bookLike.getBook().getPicUrl();
                this.bookTitle = bookLike.getBook().getTitle();
                this.writer = bookLike.getBook().getWriter();
            }

        }

    }

    // 북 댓글 페이지
    @Getter
    @Setter
    public static class BookReplyDTO {
        private Integer bookReplyConut;
        private List<ReplyDTO> replyDTOs;

        public BookReplyDTO(List<BookReply> replyDTOs) {
            this.bookReplyConut = replyDTOs.size();
            this.replyDTOs = replyDTOs.stream().map(bookreply -> new ReplyDTO(bookreply)).collect(Collectors.toList());
        }

        @Getter
        @Setter
        public class ReplyDTO {
            private Integer id;
            private String userPicUrl;
            private String nickname;
            private String replyCreatedAt;
            private String replyContent;

            public ReplyDTO(BookReply bookReply) {
                this.id = bookReply.getId();
                this.userPicUrl = bookReply.getUser().getPicUrl();
                this.nickname = bookReply.getUser().getNickname();
                this.replyCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(bookReply.getCreatedAt());
                ;
                this.replyContent = bookReply.getContent();
            }

        }
    }

}
