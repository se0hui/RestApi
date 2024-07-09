package com.back.guestboard.controller;


import com.back.guestboard.dto.BoardDto;
import com.back.guestboard.entity.User;
import com.back.guestboard.repository.UserRepo;
import com.back.guestboard.response.Response;
import com.back.guestboard.service.BoardService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController

public class BoardController {

    private final BoardService boardService;
    private final UserRepo userRepo;

    //전체 게시글 조회
    @ApiOperation(value = "전체 게시글 보기", notes = "전체 게시글을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards")
    public Response getBoards(){
        return new Response("성공", "전체 게시물 리턴", boardService.getBoards());
    }

    //개별 게시글 조회
    @ApiOperation(value = "개별 게시글 조회", notes = "개별 게시글을 조회한다")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/{id}")
    public Response getBoard(@PathVariable("id") Integer id) {
        return new Response("성공", "개별 게시물 반환", boardService.getBoard(id));
    }

    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/boards/write")
    public Response write(@RequestBody BoardDto boardDto){

        User user = userRepo.findById(1).get();
        return new Response("성공", "글 작성 성공", boardService.write(boardDto, user));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/boards/update/{id}")
    public Response edit(@RequestBody BoardDto boardDto, @PathVariable("id") Integer id){

        User user = userRepo.findById(1).get();
        return new Response("성공", "글 수정 성공", boardService.update(id, boardDto));
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/boards/delete/{id}")
    public Response delete(@PathVariable("id") Integer id){

        boardService.delete(id);
        return new Response("성공", "글 삭제 성공", null);
    }

}

