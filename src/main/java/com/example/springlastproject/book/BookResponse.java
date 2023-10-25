package com.example.springlastproject.book;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springlastproject.bookcategory.BookCategory;
import com.example.springlastproject.booklike.BookLike;
import com.example.springlastproject.bookreply.BookReply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookResponse {

    @Getter
    @Setter
    @ToString
    public static class BookDetailPageDTO {
        private Integer bookId;
        private String bookTitle;
        private String bookWriter;
        private Integer bookLikes;
        private Integer bookReplys;
        private String bookSubTitle;
        private BookDatailPage2DTO bookDatailPage2DTO;

        public BookDetailPageDTO(Book book) {
            this.bookId = book.getId();
            this.bookTitle = book.getTitle();
            this.bookWriter = book.getWriter();
            this.bookLikes = book.getBookLikeList().size();
            this.bookReplys = book.getBookReplyList().size();
            this.bookSubTitle = book.getSubTitle();
            this.bookDatailPage2DTO = new BookDatailPage2DTO(book);
        }

        @Getter
        @Setter
        @ToString
        public class BookDatailPage2DTO {
            private String introduction;
            private BookCategory bookCategory;
            private String totalPage;
            private Date publicationDate;
            private String sequence;
            private BookDatailPage3DTO bookDatailPage3DTO;

            public BookDatailPage2DTO(Book book) {
                this.introduction = book.getIntroduction();
                this.bookCategory = book.getBookCategory();
                this.totalPage = book.getTotalPage();
                this.publicationDate = book.getPublicationDate();
                this.sequence = book.getSequence();
                this.bookDatailPage3DTO = new BookDatailPage3DTO(book);
            }

        }

        @Getter
        @Setter
        @ToString
        public class BookDatailPage3DTO {
            private String writerIntroductoin;
            private String review;
            private List<BookDatailPage4DTO> bookDatailPage4DTO;

            public BookDatailPage3DTO(Book book) {
                this.writerIntroductoin = book.getIntroduction();
                this.review = book.getReview();
                this.bookDatailPage4DTO = book.getBookReplyList().stream()
                        .map(bookReply -> new BookDatailPage4DTO(bookReply, book))
                        .collect(Collectors.toList());
            }
        }

        @Getter
        @Setter
        @ToString
        public class BookDatailPage4DTO {
            private Integer replys;
            private String nickname;
            private String userPicUrl;
            private Timestamp replyCreatedAt;
            private String replyContent;

            public BookDatailPage4DTO(BookReply bookReply, Book book) {
                this.replys = book.getBookReplyList().size();
                this.nickname = bookReply.getUser().getNickname();
                this.userPicUrl = bookReply.getUser().getPicUrl();
                this.replyCreatedAt = bookReply.getCreatedAt();
                this.replyContent = bookReply.getContent();
            }

        }

    }

}
