package com.example.mfu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mfu.dto.Board;
import com.example.mfu.dto.BoardForm;
import com.example.mfu.dto.Boardfile;
import com.example.mfu.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping({"/", "/addBoard"})
	public String addBoard() {
		return "addBoard";
	}

	@PostMapping("/addBoard")
	public String addBoard(BoardForm boardForm) {
		//log.info(boardForm.toString());
		//log.info("" + boardForm.getBoardfile().size());
		boardService.addBoard(boardForm);
		return "redirect:/boardList";
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		List<Board> boardList = boardService.selectBoard();
		model.addAttribute("boardList", boardList);
		return "boardList";
	}
	
	@GetMapping("/boardOne")
	public String boardOne(@RequestParam int boardNo, Model model) {
		//log.info("" + boardNo);
		Board board = boardService.selectBoardOne(boardNo);
		List<Boardfile> list = boardService.selectBoardFile(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("list", list);
		return "boardOne";
	}
	
	@GetMapping("/deletefileOne")
	public String deletefileOne(@RequestParam String filename, @RequestParam int boardNo) {
		int row = boardService.deleteFileOne(filename);
		if (row == 0) {
			log.info("삭제실패");
		}
		return "redirect:/boardOne?boardNo=" + boardNo;
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard(@RequestParam int boardNo, Model model) {
		Board board = boardService.selectBoardOne(boardNo);
		model.addAttribute("board", board);
		return "/updateBoard";
	}
	
	@PostMapping("updateBoard")
	public String updateBoard(Board board) {
		int row = boardService.updateBoard(board);
		if (row == 0) {
			log.info("수정실패");
		}
		return "redirect:/boardOne?boardNo=" + board.getBoardNo();
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(@RequestParam int boardNo) {
		int row = boardService.deleteBoard(boardNo);
		if (row == 0) {
			log.info("삭제실패");
		}
		return "redirect:/boardList";
	}
}
