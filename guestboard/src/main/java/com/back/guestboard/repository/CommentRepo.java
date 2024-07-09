package com.back.guestboard.repository;

import com.back.guestboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByBoardId(int boardId);
}

//findBY~~ -> entity에 있는 속성으로 entity 조회가 가능합니다.