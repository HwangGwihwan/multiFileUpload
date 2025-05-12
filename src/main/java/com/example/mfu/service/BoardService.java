package com.example.mfu.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.mfu.dto.Board;
import com.example.mfu.dto.BoardForm;
import com.example.mfu.dto.Boardfile;
import com.example.mfu.exception.AddBoardException;
import com.example.mfu.mapper.BoardMapper;
import com.example.mfu.mapper.BoardfileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class BoardService {
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	BoardfileMapper boardfileMapper;
	
	public void addBoard(BoardForm boardForm) {
		// 1) board 추가
		Board board = new Board();
		board.setBoardTitle(boardForm.getBoardTitle());
		int addBoardRow = boardMapper.insertBoard(board);
		log.info("board.getBoardNo(): " + board.getBoardNo());
		if (addBoardRow != 1) {
			throw new AddBoardException();
		}
		
		// 2) boardfile 추가
		if (boardForm.getBoardfile() != null) {
			for (MultipartFile f : boardForm.getBoardfile()) {
				Boardfile boardfile = new Boardfile();
				boardfile.setBoardNo(board.getBoardNo());
				boardfile.setFiletype(f.getContentType());
				String filename = UUID.randomUUID().toString().replace("-", "");
				filename += f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
				boardfile.setFilename(filename);
				
				int addBoardfileRow = boardfileMapper.insertBoardFile(boardfile);
				if (addBoardfileRow != 1) {
					throw new AddBoardException();
				}
				
				// 3) 파일저장
				File emptyFile = new File("C:/project/upload/" + filename);
				try {
					f.transferTo(emptyFile);
				} catch (Exception e) {
					throw new AddBoardException();
				}
			}
		}
	}
	
	public List<Board> selectBoard() {
		List<Board> boardList = boardMapper.selectBoard();
		//log.info(boardList.toString());
		return boardList;
	}
	
	public Board selectBoardOne(int boardNo) {
		Board board = boardMapper.selectBoardOne(boardNo);
		return board;
	}
	
	public List<Boardfile> selectBoardFile(int boardNo) {
		List<Boardfile> fileList = boardfileMapper.selectBoardFile(boardNo);
		return fileList;
	}
	
	public int deleteFileOne(String fileName) {
		// 파일삭제
		File f = new File("c:/project/upload/" + fileName);
		if (f.exists()) {
			f.delete();	
		}
		
		int row = boardfileMapper.deleteFileOne(fileName);
		return row;
	}
	
	public int updateBoard(Board board) {
		int row = boardMapper.updateBoard(board);
		return row;
	}
	
	public int deleteBoard(int boardNo) {
		List<Boardfile> list = boardfileMapper.selectBoardFile(boardNo);
				
		for (Boardfile b : list) {
			// 파일삭제
			File f = new File("c:/project/upload/" + b.getFilename());
			if (f.exists()) {
				f.delete();	
			}
		}
		
		int row1 = boardfileMapper.deleteFile(boardNo);
		int row2 = boardMapper.deleteBoard(boardNo);
		return row2;
	}
}
