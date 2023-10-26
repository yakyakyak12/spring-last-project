package com.example.springlastproject.book;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
        private Integer bookLikeCount;
        private Integer bookReplyCount;
        private String bookSubTitle;
        private String bookIntroduction;
        private BookCategory bookCategory;
        private String totalPage;
        private Date publicationDate;
        private String sequence;
        private String writerIntroduction;
        private String review;
        private List<BookDetailReplyDTO> bookDetailReplyList;

        public BookDetailPageDTO(Book book) {
            this.bookId = book.getId();
            this.bookTitle = book.getTitle();
            this.bookWriter = book.getWriter();
            this.bookLikeCount = book.getBookLikeList().size();
            this.bookReplyCount = book.getBookReplyList().size();
            this.bookSubTitle = book.getSubTitle();
            this.bookIntroduction = book.getIntroduction();
            this.bookCategory = book.getBookCategory();
            this.totalPage = book.getTotalPage();
            this.publicationDate = book.getPublicationDate();
            this.sequence = book.getSequence();
            this.writerIntroduction = book.getIntroduction();
            this.review = book.getReview();
            this.bookDetailReplyList = book.getBookReplyList().stream()
                    .map(bookreply -> new BookDetailReplyDTO(bookreply, book)).collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public class BookDetailReplyDTO {
            private String nickname;
            private String userPicUrl;
            private String replyCreatedAt;
            private String replyContent;

            public BookDetailReplyDTO(BookReply bookReply, Book book) {
                this.nickname = bookReply.getUser().getNickname();
                this.userPicUrl = bookReply.getUser().getPicUrl();
                this.replyCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(bookReply.getCreatedAt());
                this.replyContent = bookReply.getContent();
            }

        }

    }

    @Getter
    @Setter
    @ToString
    public static class BookCategoryListDTO {
        private List<BookCategory> bookCategories;
        private List<BookListDTO> bookList;
        private Integer bookCount;

        public BookCategoryListDTO(List<BookCategory> bookCategories, List<Book> books) {
            this.bookCategories = bookCategories;
            // this.bookList = books.stream();
            this.bookCount = books.size();
        }

        @Getter
        @Setter
        @ToString
        public class BookListDTO {
            private Integer bookId;
            private Integer ranking;
            private String bookPicUrl;
            private String bookTitle;
            private String bookWriter;

            public BookListDTO(Book book) {
                this.bookId = book.getId();
                this.ranking = book.getRanking();
                this.bookPicUrl = book.getPicUrl();
                this.bookTitle = book.getSubTitle();
                this.bookWriter = book.getWriter();
            }

        }
    }

}
