package com.example.springlastproject.readingbook;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springlastproject.bookMark.BookMark;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ReadingBookResponse {

    @Getter
    @Setter
    public static class readingbookDTO {
        private List<String> bookdata;
        private List<BookMarkDTO> bookMarkDTOList;

        public readingbookDTO(List<String> bookdata, List<BookMark> bookMarkList) {
            this.bookdata = bookdata;
            this.bookMarkDTOList = bookMarkList.stream().map(bookMark -> new BookMarkDTO(bookMark)).collect(Collectors.toList());
        }
        
        @Getter
        @Setter
        public class BookMarkDTO {
            private Integer id;
            private Integer scroll;
            private String bookMarkCreatedAt;

            public BookMarkDTO(BookMark bookMark) {
                this.id = bookMark.getId();
                this.scroll = bookMark.getScroll();
                this.bookMarkCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(bookMark.getCreatedAt());
            }

    }
}

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        private Integer id;
        private Integer userId;
        private Integer bookId;

        public saveDTO(ReadingBook readingBook) {
            this.id = readingBook.getId();
            this.userId = readingBook.getUser().getId();
            this.bookId = readingBook.getBook().getId();

        }

    }

    @Getter
    @Setter
    public static class MyBookDTO {
        private List<MyreadingbookDTO> myreadingbookDTOs;

        public MyBookDTO(List<ReadingBook> readingBooks) {
            this.myreadingbookDTOs = readingBooks.stream().map(readingBook -> new MyreadingbookDTO(readingBook))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        public class MyreadingbookDTO {
            private Integer id;
            private String bookPicUrl;
            private String bookTitle;
            private String bookWriter;

            public MyreadingbookDTO(ReadingBook readingBook) {
                this.id = readingBook.getId();
                this.bookPicUrl = readingBook.getBook().getPicUrl();
                this.bookTitle = readingBook.getBook().getTitle();
                this.bookWriter = readingBook.getBook().getWriter();
            }

        }

    }

}
