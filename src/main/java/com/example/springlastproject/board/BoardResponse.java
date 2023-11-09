package com.example.springlastproject.board;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.example.springlastproject.boardlike.BoardLike;
import com.example.springlastproject.boardreply.BoardReply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class BoardResponse {
    @Getter
    @Setter
    @ToString
    public static class BoardDetailPageDTO {
        private Integer boardId;
        private Integer boardLikeCount;
        private Integer boardReplCount;
        private String boardTitle;
        private String boardUserPicUrl;
        private String boardUserNickname;
        private String boardCreatedAt;
        private String boardContent;
        private Integer bookId;
        private String bookPicUrl;
        private String bookTitle;
        private Integer boardReplyCount;
        private List<BoardReplyDTO> boardReplyDTOs;

        public BoardDetailPageDTO(Board board) {
            this.boardId = board.getId();
            this.boardLikeCount = board.getBoardLikeList().size();
            this.boardReplCount = board.getBoardReplyList().size();
            this.boardTitle = board.getBoardTitle();
            this.boardUserPicUrl = board.getUser().getPicUrl();
            this.boardUserNickname = board.getUser().getNickname();
            this.boardContent = board.getContent();
            this.bookId = board.getBook().getId();
            this.bookPicUrl = board.getBook().getPicUrl();
            this.bookTitle = board.getBook().getTitle();
            this.boardReplyCount = board.getBoardReplyList().size();
            this.boardCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(board.getCreatedAt());
            this.boardReplyDTOs = board.getBoardReplyList().stream()
                    .map(boardReply -> new BoardReplyDTO(boardReply)).collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public class BoardReplyDTO {
            private String replyUserPicUrl;
            private String replyUserNickname;
            private String replyCreatedAt;
            private String replyContent;

            public BoardReplyDTO(BoardReply boardReply) {
                this.replyUserPicUrl = boardReply.getUser().getPicUrl();
                this.replyUserNickname = boardReply.getUser().getNickname();
                this.replyCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(boardReply.getCreatedAt());
                this.replyContent = boardReply.getContent();
            }
        }
    }

    @Getter
    @Setter
    @ToString
    public static class saveDTO {
        private Integer boardId;
        private String boardTitle;
        private String content;
        private Integer bookId;
        private Integer userId;

        public saveDTO(Board board) {
            this.boardId = board.getId();
            this.boardTitle = board.getBoardTitle();
            this.content = board.getContent();
            this.bookId = (board.getBook() != null) ? board.getBook().getId() : null;
            this.userId = board.getUser().getId();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class updatePageDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer bookId;
        private String bookPicUrl;
        private String bookTitle;
        private String bookWriter;

        public updatePageDTO(Board board) {
            this.id = board.getId();
            this.title = board.getBoardTitle();
            this.content = board.getContent();
            this.bookId = board.getBook().getId();
            this.bookPicUrl = board.getBook().getPicUrl();
            this.bookTitle = board.getBook().getTitle();
            this.bookWriter = board.getBook().getWriter();
        }

    }

    @Getter
    @Setter
    @ToString
    public static class updateDTO {
        private Integer boardId;
        private String boardTitle;
        private String content;
        private Integer bookId;
        private Integer userId;

        public updateDTO() {
        }

        public updateDTO(Board board) {
            this.boardId = board.getId();
            this.boardTitle = board.getBoardTitle();
            this.content = board.getContent();
            this.bookId = (board.getBook() != null) ? board.getBook().getId() : null;
            this.userId = board.getUser().getId();
        }
    }

    @Getter
    @Setter
    @ToString
    public class BoardListDTO {
        private List<BoardDTO> boardList;

        public BoardListDTO(List<Board> boards) {
            this.boardList = boards.stream()
                    .map(board -> new BoardDTO(board)).collect(Collectors.toList());
        }

        @Getter
        @Setter
        @ToString
        public class BoardDTO {
            private Integer id;
            private String boardTitle;
            private String content;
            private String userPicUrl;
            private String userNickname;
            private String boardCreatedAt;

            public BoardDTO(Board board) {
                this.id = board.getId();
                this.boardTitle = board.getBoardTitle();
                this.content = board.getContent();
                this.userPicUrl = board.getUser().getPicUrl();
                this.userNickname = board.getUser().getNickname();
                this.boardCreatedAt = new SimpleDateFormat("yyyy-MM-dd").format(board.getCreatedAt());
            }

        }

    }

}
