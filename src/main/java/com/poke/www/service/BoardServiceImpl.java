package com.poke.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poke.www.domain.BoardVO;
import com.poke.www.repository.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService{

	@Autowired
	private final BoardMapper mapper;

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return mapper.selectList();
	}

	@Override
	public void register(BoardVO bvo) {
		mapper.insert(bvo);
		
	}

	@Override
	public BoardVO getDetail(long bno) {
		
		return mapper.selectOne(bno);
		
	}
	
}
