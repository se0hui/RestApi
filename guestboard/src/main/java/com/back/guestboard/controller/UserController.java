package com.back.guestboard.controller;


import com.back.guestboard.dto.RegisterDto;
import com.back.guestboard.response.Response;
import com.back.guestboard.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "전체 회원 보기", notes = "전체 회원을 조회한다.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public Response<?> findAll(){
        return new Response<>("true", "조회 성공", userService.findAll());
    }

    @ApiOperation(value = "유저 찾기", notes = "개별 유저 조회")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    public Response<?> findUser(@PathVariable("id") Integer id){
        return new Response<>("true", "조회 성공", userService.findUser(id));
    }

    @ApiOperation(value = "회원가입", notes = "회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth")
    public Response<?> resister(@RequestBody RegisterDto registerDto){
        return new Response<>("true", "가입 성공", userService.resister(registerDto));
    }
}
