package com.back.guestboard.repository;

import com.back.guestboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board, Integer> {

}
