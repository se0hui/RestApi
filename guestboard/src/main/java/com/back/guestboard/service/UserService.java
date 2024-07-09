package com.back.guestboard.service;

import com.back.guestboard.dto.RegisterDto;
import com.back.guestboard.entity.User;
import com.back.guestboard.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User resister(RegisterDto registerDto){
        User user = new User();
        user.setName(registerDto.getName());
        user.setPassword((registerDto.getPassword()));
        user.setUsername(registerDto.getUsername());
        return userRepo.save(user);
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findUser(int id){
        return userRepo.findById(id).orElseThrow(()->{return new IllegalArgumentException("User ID를 찾을 수 없습니다.");});
    }
}
