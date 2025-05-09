package com.example.mfu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mfu.dto.Board;
import com.example.mfu.dto.BoardForm;

@Mapper
public interface BoardMapper {
	int insertBoard(Board board);
	List<Board> selectBoard();
	Board selectBoardOne(int boardNo);
	int updateBoard(Board board);
	int deleteBoard(int boardNo);
}
