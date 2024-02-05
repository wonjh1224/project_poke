package com.poke.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poke.www.domain.BoardVO;
import com.poke.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
@Slf4j
public class BoardController {

	
	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(BoardVO bvo) {
		log.info(">>> bvo >>> {}", bvo);
		
		if(bvo.getTitle() == null || bvo.getTitle() == "" || bvo.getWriter() == null || bvo.getWriter() == "" ||
				bvo.getContent() == null || bvo.getContent() == "") {
			return "redirect:/board/list";
		}else {			
			bsv.register(bvo);
			return "redirect:/board/list";
		}
		
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		
		List<BoardVO> list = bsv.getList();
		m.addAttribute("list", list);
		
	}
	
	@GetMapping("/detail/{bno}")
	public String detail(@PathVariable("bno") long bno, Model m) {
		
		BoardVO bvo = bsv.getDetail(bno);
	
		m.addAttribute("bvo", bvo);
		
		return "/board/detail";
		
	}
	
	
}
