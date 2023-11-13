package com.example.springlastproject.book;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.example.springlastproject.board.Board;
import com.example.springlastproject.bookcategory.BookCategory;
import com.example.springlastproject.bookreply.BookReply;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookResponse {

    @Getter
    @Setter
    @ToString
    // 스토리별 카테고리 페이지
    public static class StoryBookCategoryDTO {
        private Integer storyCategoryId;
        private List<StoryBookDTO> storyBookList;

        public StoryBookCategoryDTO(Integer storyCategoryId, List<Book> bookList) {
            this.storyCategoryId = storyCategoryId;
            this.storyBookList = bookList.stream().map(book -> new StoryBookDTO(book)).collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public class StoryBookDTO {
            private Integer bookId;
            private String bookPicUrl;
            private String bookTitle;
            private String bookWriter;

            public StoryBookDTO(Book book) {
                this.bookId = book.getId();
                this.bookPicUrl = book.getPicUrl();
                this.bookTitle = book.getTitle();
                this.bookWriter = book.getWriter();
            }

        }

    }

    // 디테일 페이지 DTO
    @Getter
    @Setter
    @ToString
    public static class BookDetailPageDTO {
        private Integer bookId;
        private Integer bookLike;
        private String bookPicUrl;
        private String bookTitle;
        private String bookWriter;
        private Integer bookLikeCount;
        private Integer bookReplyCount;
        private String bookSubTitle;
        private String bookIntroduction;
        private String bookCategory;
        private String totalPage;
        private String publicationDate;
        private String sequence;
        private String writerIntroduction;
        private String review;
        private List<BookDetailReplyDTO> bookDetailReplyList;

        public BookDetailPageDTO(Book book, Integer bookLike) {
            this.bookId = book.getId();
            this.bookLike = bookLike;
            this.bookPicUrl = book.getPicUrl();
            this.bookTitle = book.getTitle();
            this.bookWriter = book.getWriter();
            this.bookLikeCount = book.getBookLikeList().size();
            this.bookReplyCount = book.getBookReplyList().size();
            this.bookSubTitle = book.getSubTitle();
            this.bookIntroduction = book.getIntroduction();
            this.bookCategory = book.getBookCategory().getCategoryName();
            this.totalPage = book.getTotalPage();
            this.publicationDate = new SimpleDateFormat("yyyy-MM-dd").format(book.getPublicationDate());
            this.sequence = book.getSequence();
            this.writerIntroduction = book.getWriterIntroductoin();
            this.review = book.getReview();
            this.bookDetailReplyList = book.getBookReplyList().stream()
                    .sorted(Comparator.comparing(BookReply::getCreatedAt).reversed())
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

    // 한달이내 출간, 서점베스트 카테고리 리스트 DTO
    @Getter
    @Setter
    @ToString
    public static class BookCategoryListDTO {
        private Integer bookCount;
        private Integer bookCategoryId;
        private List<BookListDTO> bookList;

        public BookCategoryListDTO(List<BookCategory> bookCategoryList, List<Book> books, Integer bookCategoryId) {
            this.bookCount = books.size();
            this.bookCategoryId = bookCategoryId;
            this.bookList = books.stream().map(Book -> new BookListDTO(Book)).collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public class CategoryDTO {
            private Integer categoryId;
            private String categoryName;

            public CategoryDTO(BookCategory category) {
                this.categoryId = category.getId();
                this.categoryName = category.getCategoryName();
            }

        }

        @Getter
        @Setter
        @ToString
        public class BookListDTO {
            private Integer bookId;
            private String bookPicUrl;
            private String bookTitle;
            private String bookWriter;

            public BookListDTO(Book book) {
                this.bookId = book.getId();
                this.bookPicUrl = book.getPicUrl();
                this.bookTitle = book.getTitle();
                this.bookWriter = book.getWriter();
            }

        }
    }

    // 검색 페이지
    @Getter
    @Setter
    @ToString
    public static class BookSearchPageDTO {
        List<BookCategory> bookCategories;

        public BookSearchPageDTO(List<BookCategory> bookCategories) {
            this.bookCategories = bookCategories;
        }
    }

    // 키워드 검색 DTO
    @Getter
    @Setter
    @ToString
    public static class BookSearchDTO {
        private String keyword;
        private Integer bookCount;
        private List<BookKeywordDTO> bookKeywordList;
        private Integer boardCount;
        private List<BoardKeywordDTO> boardKeywordList;

        public BookSearchDTO(List<Book> books, List<Board> boards, String ketword) {
            this.keyword = ketword;
            this.bookCount = books.size();
            this.bookKeywordList = books.stream().map(book -> new BookKeywordDTO(book)).collect(Collectors.toList());
            this.boardCount = boards.size();
            this.boardKeywordList = boards.stream().map(board -> new BoardKeywordDTO(board))
                    .collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public class BookKeywordDTO {
            private Integer bookId;
            private String bookPicUrl;
            private String bookTitle;
            private String subTitle;

            public BookKeywordDTO(Book book) {
                this.bookId = book.getId();
                this.bookPicUrl = book.getPicUrl();
                this.bookTitle = book.getTitle();
                this.subTitle = book.getSubTitle();
            }
        }

        @Getter
        @Setter
        @ToString
        public class BoardKeywordDTO {
            private Integer boardId;
            private String boardTitle;
            private String bookPicUrl;
            private String content;
            private String userPicUrl;
            private String nickname;
            private String boardCreatedAt;

            public BoardKeywordDTO(Board board) {
                this.boardId = board.getId();
                this.boardTitle = board.getBoardTitle();
                this.bookPicUrl = board.getBook().getPicUrl();
                this.content = board.getContent();
                this.userPicUrl = board.getUser().getPicUrl();
                this.nickname = board.getUser().getNickname();
                this.boardCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(board.getCreatedAt());
            }

        }

    }

    // 카테고리별 목록 페이지
    @Getter
    @Setter
    @ToString
    public static class BookCategoryDTO {
        private Integer bookCategoryId;
        private Integer bookCount;
        private List<ByCategoryPage> byCategoryPages;

        public BookCategoryDTO(Integer bookCategoryId, List<Book> books) {
            this.bookCategoryId = bookCategoryId;
            this.bookCount = books.size();
            this.byCategoryPages = books.stream().map(book -> new ByCategoryPage(book)).collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public class ByCategoryPage {
            private Integer bookId;
            private String bookPicUrl;
            private String bookTitle;
            private String bookWriter;

            public ByCategoryPage(Book book) {
                this.bookId = book.getId();
                this.bookPicUrl = book.getPicUrl();
                this.bookTitle = book.getTitle();
                this.bookWriter = book.getWriter();
            }

        }
    }

}
