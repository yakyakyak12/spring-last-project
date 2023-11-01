package com.example.springlastproject.booklike;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springlastproject.board.Board;
import com.example.springlastproject.boardreply.BoardReply;
import com.example.springlastproject.bookreply.BookReply;
import com.example.springlastproject.readingbook.ReadingBook;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BookLikeResponse {

    @Getter
    @Setter
    @ToString
    public static class checkDTO {
        private Integer bookLike;

        public checkDTO(Integer bookLike) {
            this.bookLike = bookLike;
        }
    }

    // 좋아요한 책 리스트
    @Getter
    @Setter
    @ToString
    public static class BookOfMineDTO {
        Integer bookLikeCount;
        List<LikeListDTO> likeBookList;
        List<ReadingBookDTO> readingBookList;
        PostDTO postList;

        public BookOfMineDTO(List<BookLike> booklikes, List<ReadingBook> readingBooks, List<Board> boards,
                List<BoardReply> boardReply, List<BookReply> bookReplys) {
            this.bookLikeCount = booklikes.size();
            this.likeBookList = booklikes.stream().map(booklike -> new LikeListDTO(booklike))
                    .collect(Collectors.toList());
            this.readingBookList = readingBooks.stream().map(readingbook -> new ReadingBookDTO(readingbook))
                    .collect(Collectors.toList());
            this.postList = new PostDTO(boardReply, bookReplys, boards);
        }

        @Getter
        @Setter
        @ToString
        public class LikeListDTO {
            private Integer bookLikeId;
            private Integer bookLikeUserId;
            private Integer bookId;
            private String likeBookPicUrl;
            private String likeBookTitle;
            private String likeWriter;

            public LikeListDTO(BookLike bookLike) {
                this.bookLikeId = bookLike.getId();
                this.bookLikeUserId = bookLike.getUser().getId();
                this.bookId = bookLike.getBook().getId();
                this.likeBookPicUrl = bookLike.getBook().getPicUrl();
                this.likeBookTitle = bookLike.getBook().getTitle();
                this.likeWriter = bookLike.getBook().getWriter();
            }
        }

        @Getter
        @Setter
        @ToString
        public class ReadingBookDTO {
            private Integer readingBookId;
            private Integer readingBookUserId;
            private Integer bookId;
            private String bookReadingPicUrl;
            private String bookReadingTitle;
            private String readingWriter;
            private String scroll;

            public ReadingBookDTO(ReadingBook readingBook) {
                this.readingBookId = readingBook.getId();
                this.readingBookUserId = readingBook.getUser().getId();
                this.bookId = readingBook.getBook().getId();
                this.bookReadingPicUrl = readingBook.getBook().getPicUrl();
                this.bookReadingTitle = readingBook.getBook().getTitle();
                this.readingWriter = readingBook.getBook().getWriter();
                this.scroll = readingBook.getScroll();
            }
        }

        @Getter
        @Setter
        @ToString
        public class PostDTO {
            private Integer replyCount;
            private ReplyDTO replyList;
            private Integer boardConut;
            private List<BoardDTO> boardList;

            public PostDTO(List<BoardReply> boardReplys, List<BookReply> bookReplys, List<Board> boards) {
                this.replyCount = boardReplys.size() + bookReplys.size();
                this.replyList = new ReplyDTO(boardReplys, bookReplys);
                this.boardConut = boardReplys.size();
                this.boardList = boards.stream().map(board -> new BoardDTO(board)).collect(Collectors.toList());
            }

            @Getter
            @Setter
            @ToString
            public class ReplyDTO {
                private List<BookReplyDTO> bookReplyList;
                private List<BoardReplyDTO> boardReplyList;

                public ReplyDTO(List<BoardReply> boardReplys, List<BookReply> bookReplys) {
                    this.bookReplyList = bookReplys.stream()
                            .map(bookReply -> new BookReplyDTO(bookReply)).distinct()
                            .collect(Collectors.toList());
                    this.boardReplyList = boardReplys.stream()
                            .map(boardReply -> new BoardReplyDTO(boardReply)).distinct()
                            .collect(Collectors.toList());
                }

                @Getter
                @Setter
                @ToString
                public class BookReplyDTO {
                    private Integer bookReplyId;
                    private String bookReplyContent; // 내용
                    private String bookReplyCreatedAt; // 등록일
                    private String bookPicUrl;// 북 이미지
                    private String bookTitle; // 북 제목
                    private String bookWriter; // 북 지은이

                    public BookReplyDTO(BookReply bookReply) {
                        this.bookReplyId = bookReply.getId();
                        this.bookReplyContent = bookReply.getContent();
                        this.bookReplyCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(bookReply.getCreatedAt());
                        ;
                        this.bookPicUrl = bookReply.getBook().getPicUrl();
                        this.bookTitle = bookReply.getBook().getTitle();
                        this.bookWriter = bookReply.getBook().getWriter();
                    }

                }

                @Getter
                @Setter
                @ToString
                public class BoardReplyDTO {
                    private Integer boardReplyId;
                    private String boardReplyContent; // 내용
                    private String boardReplyCreatedAt; // 등록일
                    private String bookPicUrl;// 북 이미지
                    private String bookTitle; // 북 제목
                    private String bookWriter; // 북 지은이

                    public BoardReplyDTO(BoardReply boardReply) {
                        this.boardReplyId = boardReply.getId();
                        this.boardReplyContent = boardReply.getContent();
                        this.boardReplyCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(boardReply.getCreatedAt());
                        ;
                        this.bookPicUrl = boardReply.getBoard().getBook().getPicUrl();
                        this.bookTitle = boardReply.getBoard().getBook().getTitle();
                        this.bookWriter = boardReply.getBoard().getBook().getWriter();
                    }

                }
            }

            @Getter
            @Setter
            @ToString
            public class BoardDTO {
                private Integer boardId;
                private String boardTitle;
                private String boardCreatedAt;

                public BoardDTO(Board board) {
                    this.boardId = board.getId();
                    this.boardTitle = board.getBoardTitle();
                    this.boardCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(board.getCreatedAt());

                }

            }

        }
    }
}
