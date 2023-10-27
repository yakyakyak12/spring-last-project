package com.example.springlastproject.bookreply;

import java.text.SimpleDateFormat;

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
        private String username;
        private String bookReplyContent;
        private String bookReplyCreatedAt;

        public saveDTO(BookReply bookReply, User user) {
            this.bookReplyId = bookReply.getId();
            this.userId = bookReply.getUser().getId();
            this.bookId = bookReply.getBook().getId();
            this.userPicUrl = user.getPicUrl();
            this.username = user.getUsername();
            this.bookReplyContent = bookReply.getContent();
            this.bookReplyCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(bookReply.getCreatedAt());
        }

    }

}

// @Getter
// @Setter
// @ToString
// public static class deleteDTO {
// private Integer bookReplyId;
// private String mas;

// public deleteDTO(BookReply bookReply) {
// this.bookReplyId = bookReply.getId();
// this.mas = "댓글삭제 완료";
// }

// }