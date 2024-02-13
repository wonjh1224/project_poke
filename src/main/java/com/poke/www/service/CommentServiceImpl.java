package com.poke.www.service;

import org.springframework.stereotype.Service;

import com.poke.www.domain.CommentVO;
import com.poke.www.repository.CommentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentServiceImpl implements CommentService{
	
	private final CommentMapper commentMapper;

	@Override
	public int register(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return commentMapper.insert(commentVO);
	}
	
	
}
