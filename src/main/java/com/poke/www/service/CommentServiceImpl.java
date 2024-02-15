package com.poke.www.service;

import java.util.List;

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

	@Override
	public List<CommentVO> getList(long bno) {
		// TODO Auto-generated method stub
		return commentMapper.selectList(bno);
	}

	@Override
	public int modify(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return commentMapper.update(commentVO);
	}

	@Override
	public int delete(long cno) {
		// TODO Auto-generated method stub
		return commentMapper.delete(cno);
	}
	
	
}
