package com.poke.www.controller;

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
	
	
}
