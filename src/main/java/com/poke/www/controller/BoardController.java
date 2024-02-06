package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poke.www.domain.BoardVO;
import com.poke.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
@Slf4j
public class BoardController {

	
	private final BoardService boardService;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info(">>> bvo >>> {}", bvo);
		
		if(bvo.getTitle() == null || bvo.getTitle() == "" || bvo.getWriter() == null || bvo.getWriter() == "" ||
				bvo.getContent() == null || bvo.getContent() == "") {
			log.info(">>> register bvo >>> {}", bvo);
			return "redirect:/board/list";
		}else {			
			boardService.register(bvo);
			return "redirect:/board/list";
		}
		
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		
		List<BoardVO> list = boardService.getList();
		m.addAttribute("list", list);
		
	}
	
	@GetMapping("/detail/{bno}")
	public String detail(@PathVariable("bno") long bno, Model m) {
		
		BoardVO bvo = boardService.getDetail(bno);
	
		m.addAttribute("bvo", bvo);
		
		return "/board/detail";	
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("bno") long bno) {
		boardService.delete(bno);
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo) {
		boardService.modify(bvo);
		return "redirect:/board/list";
	}
	
	
}
