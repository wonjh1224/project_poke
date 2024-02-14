package com.poke.www.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poke.www.domain.CommentVO;
import com.poke.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/comment")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CommentController {

	private final CommentService commentService;
	
	@PostMapping("/register")
	public String register(@RequestBody CommentVO commentVO) {
		log.info("commentVO : {}", commentVO);
		int isOk = commentService.register(commentVO);
		
		return isOk > 0 ? "1" : "0";
	}
	
	@GetMapping("/list/{bno}")
	public List<CommentVO> list(@PathVariable("bno")long bno){
		List<CommentVO> list = commentService.getList(bno);
		return list;
	}
	
	@PostMapping("/modify")
	public String modify(@RequestBody CommentVO commentVO) {
		int isOk = commentService.modify(commentVO);
		return isOk > 0 ? "1" : "0";
	}
	
	@DeleteMapping("/delete/{cno}")
	public String delete(@PathVariable("cno")long cno) {
		int isOk = commentService.delete(cno);
		return isOk > 0 ? "1" : "0";
	}
	
	
	
}
