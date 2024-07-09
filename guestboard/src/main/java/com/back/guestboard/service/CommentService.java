package com.back.guestboard.service;

import com.back.guestboard.dto.CommentDto;
import com.back.guestboard.entity.Board;
import com.back.guestboard.entity.Comment;
import com.back.guestboard.entity.User;
import com.back.guestboard.repository.BoardRepo;
import com.back.guestboard.repository.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepo commentRepo;
    private final BoardRepo boardRepo;

    // 댓글 작성하기
    @Transactional
    public CommentDto writeComment(int boardId, CommentDto commentDto, User user) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());

        //게시판 번호로 게시글 찾기
        Board board = boardRepo.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        });

        comment.setUser(user);
        comment.setBoard(board);
        commentRepo.save(comment);

        return CommentDto.toDto(comment);
    }

    //글에 해당하는 전체 댓글 불러오기
    @Transactional(readOnly = true)
    public List<CommentDto> getComments(int boardId){
        List<Comment> comments = commentRepo.findAllByBoardId(boardId);
        List<CommentDto> commentDtos = new ArrayList<>();

        comments.forEach(s->commentDtos.add(CommentDto.toDto(s)));
        return commentDtos;
    }


    //댓글 삭제
    @Transactional
    public String deleteComment(int commenId){
        Comment comment = commentRepo.findById(commenId).orElseThrow(()->{
            return new IllegalArgumentException("댓글 ID를 찾을 수 없습니다.");
        });
        commentRepo.deleteById(commenId);
        return "삭제 완료";
    }
}

//@Transactional 어노테이션은
//메소드가 전체 성공일 때, Commit을 해주고, 하나라도 실패한다면
//RollBack을 해준다고 보시면 됩니다.