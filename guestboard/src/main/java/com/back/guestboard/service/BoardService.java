package com.back.guestboard.service;


import com.back.guestboard.dto.BoardDto;
import com.back.guestboard.entity.Board;
import com.back.guestboard.entity.User;
import com.back.guestboard.repository.BoardRepo;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepo boardRepo;

    //전체 게시물 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getBoards() {
        List<Board> boards = boardRepo.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        boards.forEach(s -> boardDtos.add(BoardDto.toDto(s)));
        return boardDtos;
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(int id){
        Board board = boardRepo.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Board ID를 찾을 수 없습니다.");
        });
        BoardDto boardDto = BoardDto.toDto(board);
        return boardDto;
    }

    //게시물 작성
    @Transactional
    public BoardDto write(BoardDto boardDto, User user){
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setUser(user);
        boardRepo.save(board);
        return BoardDto.toDto(board);
    }

    //게시물 수정
    @Transactional
    public BoardDto update(int id, BoardDto boardDto){
        Board board = boardRepo.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Board ID를 찾을 수 없습니다!");
        });

        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());

        return BoardDto.toDto(board);
    }

    //게시글 삭제
    @Transactional
    public void delete(int id){
        //id를 기반으로, 게시글이 존재하는지 먼저 찾음
        //게시글이 없다면 오류 처리
        Board board = boardRepo.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("Board ID를 찾을 수 없습니다!");
        });

        //게시글이 있는 경우 삭제처리
        boardRepo.deleteById(id);
    }
}
